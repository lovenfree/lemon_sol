resource "aws_s3_bucket" "application_bucket" {
  bucket = var.application_bucket_name
  acl    = "private"

  versioning {
    enabled = true
  }

  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }

  tags = {
    Name      = "${var.application_bucket_name}"
    Terraform = "true"
  }
}

output "terraform_application_bucket_id" {
  value = aws_s3_bucket.application_bucket.id
}

resource "aws_s3_bucket_public_access_block" "application_bucket_policy" {
  bucket = aws_s3_bucket.application_bucket.id

  block_public_acls       = true
  block_public_policy     = true
  restrict_public_buckets = true
  ignore_public_acls      = true
}
