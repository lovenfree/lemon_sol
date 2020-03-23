environment = "dev"
aws_region  = "ap-northeast-2"

########## Terraform State file configuration
s3_bucket         = "terraform-state-vportaldev-ap-northeast-2"
s3_folder_project = "VPORTALDEV-SPRING"
s3_bucket_region  = "ap-northeast-2"
s3_tfstate_file   = "stage2-branchname.tfstate"

# prefix_name = "_PREFIXNAME_"
# ecr_uri     = "_ECRURI_"
# ecs_hostname                    = "_DOMAINURL_"
# ecs_load_balancer_external_name = "_ECS_LOAD_BALANCER_EXTERNAL_NAME_"
# ecs_alb_listener_arn            = "_ECS_ALB_LISTENER_ARN_"
# ecs_sg_internal_id              = "_ECS_SG_INTERNAL_ID_"
# vpc_id                          = "vpc-0edf11a237236e2bb"
ecs_ec2_instance_ami  = "ami-0accbb5aa909be7bf"
key_pair_name         = ""
ebs_volume_size       = "40"
container_port        = "9090"
volume_container_path = "/usr/local/volume"
# tools_zone_domain_name          = "vportaldev.koreabuild.com."
is_fargate = true
