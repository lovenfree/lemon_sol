# module "application_domain" {
#   source = "./modules/application_domain"

#   domain_name = var.app_domain_name
# }

# resource "aws_ssm_parameter" "acm_acmArn" {
#   name  = "/vpc/PARAMETER/acmArn"
#   type  = "SecureString"
#   value = module.application_domain.certificate_arn
# }

# resource "aws_ssm_parameter" "route53_hostedZone" {
#   name  = "/vpc/PARAMETER/hostedZone"
#   type  = "SecureString"
#   value = module.application_domain.hostedzone_id
# }

module "application_bucket" {
  source = "./modules/application_artifacts_s3"

  application_bucket_name = "application-artifacts-${data.aws_caller_identity.current.account_id}"
}

# resource "aws_ram_resource_share_accepter" "resolver_receiver_accept" {
#   share_arn = var.lgcns_resolver_rule_receiver_arn
# }

# data "aws_route53_resolver_rule" "lgcns_com" {
#   domain_name = var.lgcns_domain
#   rule_type   = "FORWARD"
#   depends_on = [aws_ram_resource_share_accepter.resolver_receiver_accept]
# }

# resource "aws_route53_resolver_rule_association" "sp_lgcns_resolver_association" {
#   resolver_rule_id = data.aws_route53_resolver_rule.lgcns_com.id
#   vpc_id           = module.vpc.vpc_id  

#   lifecycle {
#     ignore_changes  = [vpc_id, resolver_rule_id]
#   }
# }
