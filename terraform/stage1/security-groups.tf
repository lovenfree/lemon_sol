data "aws_subnet" "cidrs" {
  for_each = data.aws_subnet_ids.private_subnets.ids
  id       = each.value
}

# data "aws_security_group" "lambdaSG" {
#   id = var.lambdaSGID
# }

resource "aws_security_group" "ECS_SG_EXTERNAL" {
  name        = "SG-ALB-EXT-${var.prefix_name}"
  description = "Allow Access to ECS Cluster"
  vpc_id      = local.vpc_id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "TCP"
    cidr_blocks = [for s in data.aws_subnet.cidrs : s.cidr_block]
  }
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "TCP"
    cidr_blocks = var.whitelist_ips
  }

  ingress {
    from_port       = 80
    to_port         = 80
    protocol        = "TCP"
    security_groups = local.lambdaSg_endpoint
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "ECS_SG_INTERNAL" {
  name        = "SG-ALB-INT-${var.prefix_name}"
  description = "Allow Access to ECS Cluster"
  vpc_id      = data.aws_vpc.selected.id

  ingress {
    from_port   = var.container_port
    to_port     = var.container_port
    protocol    = "TCP"
    cidr_blocks = [data.aws_vpc.selected.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}


