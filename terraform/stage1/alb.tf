data "aws_vpc" "selected" {
  id = var.vpc_id
}

data "aws_subnet_ids" "public_subnets" {
  vpc_id = var.vpc_id
  filter {
    name   = "tag:Name"
    values = ["*public*"] # insert values here
  }
}

data "aws_subnet_ids" "private_subnets" {
  vpc_id = var.vpc_id
  filter {
    name   = "tag:Name"
    values = ["*private*"] # insert values here
  }
}

resource "aws_alb" "ecs_load_balancer_external" {
  name            = "ALB-EXT-${var.prefix_name}"
  security_groups = [aws_security_group.ECS_SG_EXTERNAL.id]
  subnets         = data.aws_subnet_ids.public_subnets.ids

  tags = {
    Name = "ALB-EXT-${var.prefix_name}"
  }
}

resource "aws_lb_listener" "ecs_alb_listener" {
  load_balancer_arn = aws_alb.ecs_load_balancer_external.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type = "fixed-response"

    fixed_response {
      content_type = "text/plain"
      message_body = "Health OK"
      status_code  = "200"
    }
  }
}

# resource "aws_alb_listener" "ecs_alb_listener" {
#   load_balancer_arn = aws_alb.ecs_load_balancer_external.arn
#   port              = "80"
#   protocol          = "HTTP"
#   # ssl_policy        = "ELBSecurityPolicy-2016-08"
#   # certificate_arn   = aws_acm_certificate.tools_zone_cert.arn

#   default_action {
#     target_group_arn = aws_alb_target_group.ecs_target_group_external.arn
#     type             = "forward"
#   }
# }

