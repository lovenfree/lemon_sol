
resource "aws_ecs_task_definition" "ecs_task_definition_for_fargate" {
  count                    = var.is_fargate ? 1 : 0
  family                   = "TDN-${var.prefix_name}"
  network_mode             = "awsvpc"
  task_role_arn            = aws_iam_role.ecs_task_role.arn
  execution_role_arn       = aws_iam_role.ecs_task_role.arn
  cpu                      = 256
  memory                   = 512
  requires_compatibilities = ["FARGATE"]
  container_definitions    = data.template_file.ecs_task.rendered
}

resource "aws_ecs_service" "ecs_service_for_fargate" {
  count                              = var.is_fargate ? 1 : 0
  name                               = "SVC-${var.prefix_name}"
  cluster                            = aws_ecs_cluster.ecs_cluster.id
  task_definition                    = aws_ecs_task_definition.ecs_task_definition_for_fargate[count.index].arn
  launch_type                        = "FARGATE"
  desired_count                      = 1
  deployment_minimum_healthy_percent = 100
  deployment_maximum_percent         = 200

  network_configuration {
    security_groups  = [var.ecs_sg_internal_id]
    subnets          = data.aws_subnet_ids.private_subnets.ids
    assign_public_ip = true
  }
  load_balancer {
    target_group_arn = aws_alb_target_group.ecs_target_group_external.arn
    container_port   = var.container_port
    container_name   = local.container_name
  }
  depends_on = [aws_ecs_task_definition.ecs_task_definition_for_fargate]
}

resource "aws_iam_role" "ecs_autoscale_role" {
  count              = var.is_fargate ? 1 : 0
  name               = "ecs-asrole-${var.prefix_name}"
  assume_role_policy = file("${path.module}/policies/ecs_autoscale_iam_role.json")
}

resource "aws_iam_role_policy" "ecs_autoscale_role_policy" {
  count  = var.is_fargate ? 1 : 0
  name   = "ecs-asrole-policy-${var.prefix_name}"
  role   = aws_iam_role.ecs_autoscale_role[count.index].id
  policy = file("${path.module}/policies/ecs_autoscale_iam_role_policy.json")
}

# ---------------------------------------------------------------------------------------------------------------------
# AWS Auto Scaling - CloudWatch Alarm CPU High
# ---------------------------------------------------------------------------------------------------------------------
resource "aws_cloudwatch_metric_alarm" "cpu_high" {
  count               = var.is_fargate ? 1 : 0
  alarm_name          = "cpu-high-${var.prefix_name}"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  evaluation_periods  = "3"
  metric_name         = "CPUUtilization"
  namespace           = "AWS/ECS"
  period              = "60"
  statistic           = "Maximum"
  threshold           = "85"
  dimensions = {
    ClusterName = aws_ecs_cluster.ecs_cluster.name
    ServiceName = aws_ecs_service.ecs_service_for_fargate[count.index].name
  }
  alarm_actions = [aws_appautoscaling_policy.scale_up_policy[count.index].arn]
}

# ---------------------------------------------------------------------------------------------------------------------
# AWS Auto Scaling - CloudWatch Alarm CPU Low
# ---------------------------------------------------------------------------------------------------------------------
resource "aws_cloudwatch_metric_alarm" "cpu_low" {
  count               = var.is_fargate ? 1 : 0
  alarm_name          = "cpu-low-${var.prefix_name}"
  comparison_operator = "LessThanOrEqualToThreshold"
  evaluation_periods  = "3"
  metric_name         = "CPUUtilization"
  namespace           = "AWS/ECS"
  period              = "60"
  statistic           = "Average"
  threshold           = "10"
  dimensions = {
    ClusterName = aws_ecs_cluster.ecs_cluster.name
    ServiceName = aws_ecs_service.ecs_service_for_fargate[count.index].name
  }
  alarm_actions = [aws_appautoscaling_policy.scale_down_policy[count.index].arn]
}

# ---------------------------------------------------------------------------------------------------------------------
# AWS Auto Scaling - Scaling Up Policy
# ---------------------------------------------------------------------------------------------------------------------
resource "aws_appautoscaling_policy" "scale_up_policy" {
  count              = var.is_fargate ? 1 : 0
  name               = "scale-up-policy-${var.prefix_name}"
  depends_on         = [aws_appautoscaling_target.scale_target]
  service_namespace  = "ecs"
  resource_id        = "service/${aws_ecs_cluster.ecs_cluster.name}/${aws_ecs_service.ecs_service_for_fargate[count.index].name}"
  scalable_dimension = "ecs:service:DesiredCount"
  step_scaling_policy_configuration {
    adjustment_type         = "ChangeInCapacity"
    cooldown                = 60
    metric_aggregation_type = "Maximum"
    step_adjustment {
      metric_interval_lower_bound = 0
      scaling_adjustment          = 1
    }
  }
}

# ---------------------------------------------------------------------------------------------------------------------
# AWS Auto Scaling - Scaling Down Policy
# ---------------------------------------------------------------------------------------------------------------------
resource "aws_appautoscaling_policy" "scale_down_policy" {
  count              = var.is_fargate ? 1 : 0
  name               = "scale-down-policy-${var.prefix_name}"
  depends_on         = [aws_appautoscaling_target.scale_target]
  service_namespace  = "ecs"
  resource_id        = "service/${aws_ecs_cluster.ecs_cluster.name}/${aws_ecs_service.ecs_service_for_fargate[count.index].name}"
  scalable_dimension = "ecs:service:DesiredCount"
  step_scaling_policy_configuration {
    adjustment_type         = "ChangeInCapacity"
    cooldown                = 60
    metric_aggregation_type = "Maximum"
    step_adjustment {
      metric_interval_lower_bound = 0
      scaling_adjustment          = -1
    }
  }
}

# ---------------------------------------------------------------------------------------------------------------------
# AWS Auto Scaling - Scaling Target
# ---------------------------------------------------------------------------------------------------------------------
resource "aws_appautoscaling_target" "scale_target" {
  count              = var.is_fargate ? 1 : 0
  service_namespace  = "ecs"
  resource_id        = "service/${aws_ecs_cluster.ecs_cluster.name}/${aws_ecs_service.ecs_service_for_fargate[count.index].name}"
  scalable_dimension = "ecs:service:DesiredCount"
  role_arn           = aws_iam_role.ecs_autoscale_role[count.index].arn
  min_capacity       = 1
  max_capacity       = 1
}
