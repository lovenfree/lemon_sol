
module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "2.15.0"

  name               = "vportal-vpc"
  cidr               = "10.1.106.0/25"
  azs                = var.vpc_azs
  private_subnets    = ["10.1.106.0/27", "10.1.106.32/27"]
  public_subnets     = ["10.1.106.64/27", "10.1.106.96/27"]
  enable_nat_gateway = true
  # 외부에서 들어오는 요청은 api-gateway에서 받으며, 인터넷향 요청이 서비스에 영향 줄 일은 없어 단일 nat 사용해도 서비스에 영향 없을 것으로 보임
  single_nat_gateway   = true
  enable_dns_hostnames = true

  # tags = {
  #   "kubernetes.io/cluster/uas-multi-dev" = "shared" 
  # }

  # public_subnet_tags = {
  #   "kubernetes.io/role/elb"                      = "1"
  #   "kubernetes.io/cluster/uas-multi-dev" = "shared"
  # }

  # private_subnet_tags = {
  #   "kubernetes.io/role/internal-elb"             = "1"
  #   "kubernetes.io/cluster/uas-multi-dev" = "shared"
  # }
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
  name  = "/vpc/PARAMETER/natGateWayPubIp"
  type  = "SecureString"
  value = join(",", [
    for nat_public_ip in module.vpc.nat_public_ips:
      "${nat_public_ip}/32"
  ])
}