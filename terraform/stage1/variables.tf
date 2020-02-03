variable "aws_region" {
  type = string
}
variable "environment" {
  type = string
}
variable "assume_role_arn" {
  type = string
}
variable "s3_bucket" {
  type = string
}
variable "s3_bucket_region" {
  type = string
}
variable "s3_folder_project" {
  type = string
}
variable "s3_tfstate_file" {
  type = string
}
variable "vpc_id" {
  type = string
}
variable "whitelist_ips" {
  type = list
}
variable "prefix_name" {
  type = string
}
variable "container_port" {
  type = string
}
