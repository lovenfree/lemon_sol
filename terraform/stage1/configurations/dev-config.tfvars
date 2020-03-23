environment = "dev"
aws_region  = "ap-northeast-2"

########## Terraform State file configuration
s3_bucket         = "terraform-state-vportaldev-ap-northeast-2"
s3_folder_project = "VPORTALDEV-SPRING"
s3_bucket_region  = "ap-northeast-2"
s3_tfstate_file   = "stage1-common.tfstate"
# hosted_zone_domain_name = "*.vportaldev.buildtestenv.com"

prefix_name    = "V-P-I-COMMON"
container_port = "9090"
whitelist_ips = [
  "165.243.5.20/32"
]

########## ALB Logging configuration
# s3_account_id         = "151564769076"
logging_bucket_prefix = "v-p-i-accesslog"

#"ap-northeast-2"
elb_account_id = "600734575887"

setting_alb_deletion_protection = false
