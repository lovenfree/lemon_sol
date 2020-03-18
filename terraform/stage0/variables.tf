variable "aws_region" {
  type = string
}

variable "environment" {
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

########## VPC configuration
variable "vpc_azs" {
  type = list
}
