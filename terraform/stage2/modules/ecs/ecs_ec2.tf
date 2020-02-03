
resource "aws_ecs_task_definition" "ecs_task_definition_for_ec2" {
  count                 = var.is_fargate ? 0 : 1
  family                = "TDN-${var.prefix_name}"
  task_role_arn         = aws_iam_role.ecs_task_role.arn
  cpu                   = 256
  memory                = 512
  container_definitions = data.template_file.ecs_task.rendered
  volume {
    name = local.volume_name
    docker_volume_configuration {
      driver        = "cloudstor:aws"
      scope         = "shared"
      autoprovision = true
      driver_opts = {
        "ebstype" = "gp2"
        "size"    = var.ebs_volume_size
        "backing" = "relocatable"
      }
    }
  }

  volume {
    name      = "docker_sock"
    host_path = "/var/run/docker.sock"
  }
}

resource "aws_ecs_service" "ecs_service_for_ec2" {
  count                              = var.is_fargate ? 0 : 1
  name                               = "SVC-${var.prefix_name}"
  cluster                            = aws_ecs_cluster.ecs_cluster.id
  task_definition                    = aws_ecs_task_definition.ecs_task_definition_for_ec2[count.index].arn
  desired_count                      = 1
  deployment_minimum_healthy_percent = 100
  deployment_maximum_percent         = 200

  load_balancer {
    target_group_arn = aws_alb_target_group.ecs_target_group_external.arn
    container_port   = var.container_port
    container_name   = local.container_name
  }
  depends_on = [aws_ecs_task_definition.ecs_task_definition_for_fargate]
}

resource "aws_launch_configuration" "ecs_ec2_launch_configuration" {
  count                = var.is_fargate ? 0 : 1
  name_prefix          = "LAUNCH-CONFIG-${var.prefix_name}-"
  image_id             = var.ecs_ec2_instance_ami
  instance_type        = "t2.large"
  iam_instance_profile = aws_iam_instance_profile.ecs_instance_profile.name

  root_block_device {
    volume_type           = "gp2"
    volume_size           = "40"
    delete_on_termination = "true"
  }

  lifecycle {
    create_before_destroy = true
  }

  security_groups = [var.ecs_sg_internal_id]
  key_name        = var.key_pair_name
  user_data       = data.template_file.ec2_user_data.rendered
}

resource "aws_autoscaling_group" "ecs_autoscaling_group" {
  count                = var.is_fargate ? 0 : 1
  name_prefix          = "ECS-ASG-${var.prefix_name}-"
  max_size             = 1
  min_size             = 1
  desired_capacity     = 1
  vpc_zone_identifier  = data.aws_subnet_ids.private_subnets.ids
  launch_configuration = aws_launch_configuration.ecs_ec2_launch_configuration[count.index].name
  tag {
    key                 = "Name"
    value               = var.prefix_name
    propagate_at_launch = true
  }
}
