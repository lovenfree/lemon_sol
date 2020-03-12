resource "aws_ssm_parameter" "ECS_SG_EXTERNAL_ID" {
  name  = "/V-P-I/ECS_SG_EXTERNAL_ID"
  type  = "SecureString"
  value = aws_security_group.ECS_SG_EXTERNAL.id
}

resource "aws_ssm_parameter" "ECS_SG_INTERNAL_ID" {
  name  = "/V-P-I/ECS_SG_INTERNAL_ID"
  type  = "SecureString"
  value = aws_security_group.ECS_SG_INTERNAL.id
}

resource "aws_ssm_parameter" "ECS_LOAD_BALANCER_EXTERNAL_NAME" {
  name  = "/V-P-I/ECS_LOAD_BALANCER_EXTERNAL_NAME"
  type  = "SecureString"
  value = aws_alb.ecs_load_balancer_external.name
}

resource "aws_ssm_parameter" "ECS_ALB_LISTENER_ARN" {
  name  = "/V-P-I/ECS_ALB_LISTENER_ARN"
  type  = "SecureString"
  value = aws_lb_listener.ecs_alb_listener.arn
}

# #Fowler
# resource "aws_ssm_parameter" "ECS_ALB_PRI_LISTENER_ARN" {
#   name  = "/V-P-I/ECS_ALB_PRI_LISTENER_ARN"
#   type  = "SecureString"
#   value = aws_lb_listener.ecs_alb_pri_listener.arn
# }
# #Fowler
