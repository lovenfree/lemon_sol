variable "aws_region" {}
variable "environment" {}
variable "assume_role_arn" {}
variable "s3_bucket" {}
variable "s3_bucket_region" {}
variable "s3_folder_project" {}
variable "s3_tfstate_file" {}
variable "vpc_id" {}
variable "ecr_uri" {}
variable "container_port" {}
variable "ecs_hostname" {}
variable "ecs_ec2_instance_ami" {}
variable "key_pair_name" {}
variable "ebs_volume_size" {}
variable "volume_container_path" {}
variable "tools_zone_domain_name" {}
variable "prefix_name" {}
variable "ecs_load_balancer_external_name" {}
variable "ecs_alb_listener_arn" {}
variable "ecs_sg_internal_id" {}
variable "whitelist_ips" {
  type = list
}
variable "is_fargate" {
  type = bool
}
