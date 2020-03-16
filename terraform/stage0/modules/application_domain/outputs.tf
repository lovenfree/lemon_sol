output "certificate_arn" {
  value = aws_acm_certificate_validation.application_cert.certificate_arn
}

output "hostedzone_id" {
  value = aws_route53_zone.application.id
}
