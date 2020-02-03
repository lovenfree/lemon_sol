data "template_file" "ec2_user_data" {
  template = "${file("${path.module}/user_data/ec2_user_data.sh")}"

  vars = {
    cluster_name = local.cluster_name
    region       = var.region
  }
}

data "template_file" "ecs_task" {
  template = var.is_fargate ? file("${path.module}/container_definitions/ecs_task_definition_fargate.json") : file("${path.module}/container_definitions/ecs_task_definition.json")

  vars = {
    container_name              = local.container_name
    container_port              = var.container_port
    aws_region                  = var.region
    log_group                   = aws_cloudwatch_log_group.cloudwatch_log_group.id
    ecr_uri                     = var.ecr_uri
    volume_mount_container_path = "/tmp"
    volume_name                 = local.volume_name
    volume_container_path       = var.volume_container_path
  }
}

data "template_file" "ecs_role_ebs" {
  template = file("${path.module}/policies/ecs_role_ebs.json")
}

data "template_file" "ecs_task_role_policy" {
  template = file("${path.module}/policies/ecs_task_role_policy.json")
  vars = {
    region           = var.region
    aws_account_id   = data.aws_caller_identity.current.account_id
    log_group_arn    = aws_cloudwatch_log_group.cloudwatch_log_group.arn
    ecs_cluster_name = aws_ecs_cluster.ecs_cluster.name
  }
}
