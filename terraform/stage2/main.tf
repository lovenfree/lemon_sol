data "aws_ssm_parameter" "ECR_URI" {
  name = "/V-P-I/ECR-URI"
}

data "aws_ssm_parameter" "VPC_ID" {
  name = "/vpc/PARAMETER/vpcId"
}

data "aws_ssm_parameter" "ECS_LOAD_BALANCER_EXTERNAL_NAME" {
  name = "/V-P-I/ECS_LOAD_BALANCER_EXTERNAL_NAME"
}

data "aws_ssm_parameter" "ECS_ALB_LISTENER_ARN" {
  name = "/V-P-I/ECS_ALB_LISTENER_ARN"
}

data "aws_ssm_parameter" "ECS_SG_INTERNAL_ID" {
  name = "/V-P-I/ECS_SG_INTERNAL_ID"
}

module "ECS-INTF-SPRING" {
  source         = "./modules/ecs"
  region         = var.aws_region
  prefix_name    = "PREFIX"
  container_port = var.container_port
  # ecs_hostname           = var.ecs_hostname
  ecs_ec2_instance_ami  = var.ecs_ec2_instance_ami
  key_pair_name         = var.key_pair_name
  ebs_volume_size       = "40"
  volume_container_path = var.volume_container_path
  # tools_zone_domain_name = var.tools_zone_domain_name
  vpc_id                          = data.aws_ssm_parameter.VPC_ID.value
  ecr_uri                         = data.aws_ssm_parameter.ECR_URI.value
  ecs_load_balancer_external_name = data.aws_ssm_parameter.ECS_LOAD_BALANCER_EXTERNAL_NAME.value
  ecs_alb_listener_arn            = data.aws_ssm_parameter.ECS_ALB_LISTENER_ARN.value
  ecs_sg_internal_id              = data.aws_ssm_parameter.ECS_SG_INTERNAL_ID.value
  is_fargate                      = var.is_fargate
}
