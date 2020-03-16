provider "aws" {
  alias  = "east"
  region = "us-east-1"
}

resource "aws_route53_zone" "application" {
  name = var.domain_name
}

resource "aws_acm_certificate" "application_ssl_cert" {
  provider          = aws.east
  domain_name       = "*.${var.domain_name}"
  validation_method = "DNS"

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_route53_record" "cert_validation" {
  name    = aws_acm_certificate.application_ssl_cert.domain_validation_options.0.resource_record_name
  type    = aws_acm_certificate.application_ssl_cert.domain_validation_options.0.resource_record_type
  zone_id = aws_route53_zone.application.id
  records = ["${aws_acm_certificate.application_ssl_cert.domain_validation_options.0.resource_record_value}"]
  ttl     = 60
}

resource "aws_acm_certificate_validation" "application_cert" {
  provider                = aws.east
  certificate_arn         = aws_acm_certificate.application_ssl_cert.arn
  validation_record_fqdns = ["${aws_route53_record.cert_validation.fqdn}"]
}
