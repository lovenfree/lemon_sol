
module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "2.15.0"

  name                 = "vportal-vpc"
  cidr                 = "10.1.106.0/25"
  azs                  = var.vpc_azs
  private_subnets      = ["10.1.106.0/27", "10.1.106.32/27"]
  public_subnets       = ["10.1.106.64/27", "10.1.106.96/27"]
  enable_nat_gateway   = true
  single_nat_gateway   = true
  enable_dns_hostnames = true
}

resource "aws_ssm_parameter" "vpc_vpcId" {
  name  = "/vpc/PARAMETER/vpcId"
  type  = "SecureString"
  value = module.vpc.vpc_id
}

resource "aws_ssm_parameter" "vpc_privateSubnet1" {
  name = "/vpc/PARAMETER/privateSubnet1"
  type = "SecureString"
  #  value = "element(${module.vpc.private_subnets},0)"
  value = module.vpc.private_subnets[0]
}

resource "aws_ssm_parameter" "vpc_privateSubnet2" {
  name  = "/vpc/PARAMETER/privateSubnet2"
  type  = "SecureString"
  value = module.vpc.private_subnets[1]
}

resource "aws_ssm_parameter" "vpc_natGateWayPubIp" {
  name = "/vpc/PARAMETER/natGateWayPubIp"
  type = "SecureString"
  value = join(",", [
    for nat_public_ip in module.vpc.nat_public_ips :
    "${nat_public_ip}/32"
  ])
}
