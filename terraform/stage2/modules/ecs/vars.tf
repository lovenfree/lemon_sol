data "aws_caller_identity" "current" {}

variable "region" {}
variable "prefix_name" {}
variable "container_port" {}
variable "volume_container_path" {}
variable "ecs_hostname" {}
variable "ecs_ec2_instance_ami" {}
variable "key_pair_name" {}
variable "ebs_volume_size" {}
variable "tools_zone_domain_name" {}
variable "vpc_id" {}
variable "ecr_uri" {}
variable "ecs_load_balancer_external_name" {}
variable "ecs_alb_listener_arn" {}
variable "ecs_sg_internal_id" {}
variable "is_fargate" {
  type = bool
}
variable "my_ips" {
  type = list
}
