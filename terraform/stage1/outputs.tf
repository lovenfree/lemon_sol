output "access_log_bucket_name" {
  value = aws_s3_bucket.access_log_bucket.id
}
output "access_log_bucket_arn" {
  value = aws_s3_bucket.access_log_bucket.arn
}