resource "aws_alb_target_group" "ecs_target_group_external" {
  name        = "TG-EXT-${var.prefix_name}"
  protocol    = "HTTP"
  target_type = var.is_fargate ? "ip" : "instance"
  port        = var.container_port
  vpc_id      = var.vpc_id

  health_check {
    healthy_threshold   = "2"
    unhealthy_threshold = "5"
    timeout             = "20"
    interval            = "30"
    matcher             = "200"
    path                = "/"
    port                = "traffic-port"
    protocol            = "HTTP"
  }

  tags = {
    Name = "ALB-TG-EXT-${var.prefix_name}"
  }
}

resource "aws_lb_listener_rule" "ecs_alb_listener_rule" {
  listener_arn = var.ecs_alb_listener_arn

  action {
    type             = "forward"
    target_group_arn = aws_alb_target_group.ecs_target_group_external.arn
  }

  condition {
    host_header {
      values = [var.ecs_hostname]
    }
  }
}

resource "aws_route53_record" "ecs_route53_record" {
  zone_id = data.aws_route53_zone.selected.zone_id
  name    = var.ecs_hostname
  type    = "A"

  alias {
    name                   = data.aws_alb.ECS_LOAD_BALANCER_EXTERNAL.dns_name
    zone_id                = data.aws_alb.ECS_LOAD_BALANCER_EXTERNAL.zone_id
    evaluate_target_health = false
  }
}