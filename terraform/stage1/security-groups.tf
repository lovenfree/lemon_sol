
resource "aws_security_group" "ECS_SG_EXTERNAL" {
  name        = "SG-ALB-EXT-${var.prefix_name}"
  description = "Allow Access to ECS Cluster"
  vpc_id      = var.vpc_id

  ingress {
    from_port = 80
    to_port   = 80
    protocol  = "TCP"

    cidr_blocks = var.whitelist_ips
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

