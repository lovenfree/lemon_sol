environment = "dev"
aws_region  = "ap-northeast-2"

########## Terraform State file configuration
s3_bucket         = "terraform-state-vportaldev-ap-northeast-2"
s3_folder_project = "VPORTALDEV-INFRA"
s3_bucket_region  = "ap-northeast-2"
s3_tfstate_file   = "VPORTALDEV.tfstate"

########## VPC configuration
vpc_azs = ["ap-northeast-2a", "ap-northeast-2c"]
