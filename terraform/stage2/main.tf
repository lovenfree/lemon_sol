
module "ECS-INTF-SPRING" {
  source                 = "./modules/ecs"
  region                 = var.aws_region
  prefix_name            = var.prefix_name
  container_port         = var.container_port
  ecs_hostname           = var.ecs_hostname
  ecs_ec2_instance_ami   = var.ecs_ec2_instance_ami
  key_pair_name          = var.key_pair_name
  ebs_volume_size        = "40"
  volume_container_path  = var.volume_container_path
  tools_zone_domain_name = var.tools_zone_domain_name
  vpc_id                 = var.vpc_id
  ecr_uri                = var.ecr_uri
  my_ips                 = var.whitelist_ips
  ecs_load_balancer_external_name = var.ecs_load_balancer_external_name
  ecs_alb_listener_arn   = var.ecs_alb_listener_arn
  ecs_sg_internal_id     = var.ecs_sg_internal_id
  is_fargate             = var.is_fargate
}
