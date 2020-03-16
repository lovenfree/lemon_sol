resource "aws_iam_role" "iam-rds-monitor-role" {
  name = "iam-rds-monitor-role"
  assume_role_policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": [
          "monitoring.rds.amazonaws.com"
        ]
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
POLICY
}

# IAM Policy attachment
resource "aws_iam_role_policy_attachment" "iam-rds-monitor-policy-attachment" {
  role       = aws_iam_role.iam-rds-monitor-role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonRDSEnhancedMonitoringRole"
}
