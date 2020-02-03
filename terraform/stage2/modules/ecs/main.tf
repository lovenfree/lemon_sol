data "aws_vpc" "selected" {
  id = var.vpc_id
}

data "aws_subnet_ids" "public_subnets" {
  vpc_id = var.vpc_id
  filter {
    name   = "tag:Name"
    values = ["*public*"] # insert values here
  }
}

data "aws_subnet_ids" "private_subnets" {
  vpc_id = var.vpc_id
  filter {
    name   = "tag:Name"
    values = ["*private*"] # insert values here
  }
}

data "aws_alb" "ECS_LOAD_BALANCER_EXTERNAL" {
  name  = var.ecs_load_balancer_external_name
}

data "aws_route53_zone" "selected" {
  name         = var.tools_zone_domain_name
  private_zone = false
}

resource "aws_ecs_cluster" "ecs_cluster" {
  name = local.cluster_name
}