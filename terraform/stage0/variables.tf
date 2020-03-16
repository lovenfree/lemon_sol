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

variable "config_bucket_prefix" {
  default = "config"
}

variable "config_bucket_key_prefix" {
  default = "config"
}

########## VPC configuration
variable "vpc_azs" {
  type = list
}

########## Route53 configuration
# variable "app_domain_name" {
#   type = string
# }

# ############ direct connect
# variable "dx_gwid" {
#   type = string
# }

# variable "dx_owner_acct_id" {
#   type = string
# }

# ############# transit gateway
# variable "tgw_ram_receiver_arn" {
#   type = string
# }

# variable "sp_tgw_id" {
#   type = string
# }

# variable "sp_cidr" {
#   type = string
# }

########## SG
# variable "vpc_id" {
#   type = string
# }

########## RDS
variable "allocated_storage" {
  type = string
}
variable "storage_type" {
  type = string
}
variable "engine" {
  type = string
}
variable "engine_version" {
  type = string
}
variable "instance_class" {
  type = string
}
variable "instance_name_prefix" {
  type = string
}
variable "db_name" {
  type = string
}
variable "username" {
  type = string
}
# variable "password" {
#   type        = string
# }
variable "port" {
  type = string
}
# variable "db_subnets" {
#   type = list(string)
# }
variable "publicly_accessible" {
  type = string
}
variable "storage_encrypted" {
  type = string
}
variable "skip_final_snapshot" {
  type = string
}

# ########## KMS
# variable "app_key_desc" {
#   type = string
# }

# variable "app_key_name" {
#   type = string
# }

########## DB ACCESS CONTROLE
variable "db_access_controle_cidr" {
  type = string
}

############# lgcns.com resolver rule 
# variable "lgcns_resolver_rule_receiver_arn" {
#   type = string
# }

############# lgcns.com domain
# variable "lgcns_domain" {
#   type = string
# }
