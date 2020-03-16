data "aws_ssm_parameter" "vpc_vpcId" {
  name = "/vpc/PARAMETER/vpcId"
}

data "aws_ssm_parameter" "lambdaSg_endpoint" {
  name = "/vpc/PARAMETER/lambdaSecurityGroupId"
}

locals {
  vpc_id            = data.aws_ssm_parameter.vpc_vpcId.value
  lambdaSg_endpoint = [data.aws_ssm_parameter.lambdaSg_endpoint.value]
}

data "aws_vpc" "selected" {
  id = local.vpc_id
}

data "aws_subnet_ids" "public_subnets" {
  vpc_id = local.vpc_id
  filter {
    name   = "tag:Name"
    values = ["*public*"] # insert values here
  }
}

data "aws_subnet_ids" "private_subnets" {
  vpc_id = local.vpc_id
  filter {
    name   = "tag:Name"
    values = ["*private*"] # insert values here
  }
}

data "aws_caller_identity" "current" {}

resource "aws_s3_bucket" "access_log_bucket" {
  bucket = "${var.logging_bucket_prefix}-${data.aws_caller_identity.current.account_id}-${var.aws_region}"
  # acl    = "private"
  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }
  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "AllowPutObject",
      "Principal": {"AWS": "${var.elb_account_id}"},
      "Action": [
        "s3:PutObject"
      ],
      "Effect": "Allow",
      "Resource": "arn:aws:s3:::${var.logging_bucket_prefix}-${data.aws_caller_identity.current.account_id}-${var.aws_region}/AWSLogs/*"
      }
    ]
}
EOF
  region = var.aws_region
}


resource "aws_alb" "ecs_load_balancer_external" {
  name            = "ALB-EXT-PRI-${var.prefix_name}"
  security_groups = [aws_security_group.ECS_SG_EXTERNAL.id]
  subnets         = data.aws_subnet_ids.public_subnets.ids
  # internal        = "true"

  enable_deletion_protection = var.setting_alb_deletion_protection

  access_logs {
    bucket  = aws_s3_bucket.access_log_bucket.bucket
    enabled = "true"
  }

  tags = {
    Name = "ALB-EXT-PRI-${var.prefix_name}"
  }
  depends_on = [aws_s3_bucket.access_log_bucket]
}




resource "aws_lb_listener" "ecs_alb_listener" {
  load_balancer_arn = aws_alb.ecs_load_balancer_external.arn
  port              = "80"
  protocol          = "HTTP"
  # ssl_policy        = "ELBSecurityPolicy-2016-08"
  # certificate_arn   = aws_acm_certificate.hosted_zone_cert.arn

  default_action {
    type = "fixed-response"

    fixed_response {
      content_type = "text/plain"
      message_body = "Health OK"
      status_code  = "200"
    }
  }
}

# resource "aws_acm_certificate" "hosted_zone_cert" {
#   domain_name       = var.hosted_zone_domain_name
#   validation_method = "DNS"

#   lifecycle {
#     create_before_destroy = true
#   }
# }

# resource "aws_lb_listener_certificate" "tools_zone_cert" {
#   listener_arn    = aws_lb_listener.ecs_alb_listener.arn
#   certificate_arn = aws_acm_certificate.hosted_zone_cert.arn
# }

