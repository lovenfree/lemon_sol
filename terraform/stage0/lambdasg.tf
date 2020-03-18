# for application lambda to use vpc
resource "aws_security_group" "lambda_security_group" {
  name   = "SG-COMMON-Lambda-SecurityGroup"
  vpc_id = module.vpc.vpc_id

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_ssm_parameter" "lambdaSg_endpoint" {
  name  = "/vpc/PARAMETER/lambdaSecurityGroupId"
  type  = "SecureString"
  value = aws_security_group.lambda_security_group.id
}
