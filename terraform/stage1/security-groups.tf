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
  vpc_id      = var.vpc_id

  ingress {
    from_port = 443
    to_port   = 443
    protocol  = "TCP"
    cidr_blocks = [for s in data.aws_subnet.cidrs : s.cidr_block]
  }
  ingress {
    from_port = 443
    to_port   = 443
    protocol  = "TCP"
    cidr_blocks = var.whitelist_ips
  }

  ingress {
    from_port = 443
    to_port   = 443
    protocol  = "TCP"
    cidr_blocks = ["10.1.106.118/32","10.1.106.122/32"]
  }
  
 ingress {
    from_port = 443
    to_port   = 443
    protocol  = "TCP"
    security_groups = var.lambdaSGID
  }

  #  ingress {
  #   from_port = 80
  #   to_port   = 80
  #   protocol  = "TCP"
  #   security_groups = [data.aws_security_group.lambdaSG.id]
  # }
  
  ingress {
    from_port   = 9100
    to_port     = 9100
    protocol    = "TCP"
    description = "for communication of prometheus node exporter"
    cidr_blocks = [data.aws_vpc.selected.cidr_block]
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

  ingress {
    from_port   = 9100
    to_port     = 9100
    protocol    = "TCP"
    description = "for communication of prometheus node exporter"
    cidr_blocks = [data.aws_vpc.selected.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}


