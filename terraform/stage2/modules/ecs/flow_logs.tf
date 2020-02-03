resource "aws_cloudwatch_log_group" "cloudwatch_log_group" {
  name = "${var.prefix_name}_logs"
}
