resource "aws_iam_role" "ecs_task_role" {
  name               = "IAM-ECS-TASK-${var.prefix_name}"
  assume_role_policy = <<EOF
{
  "Version": "2008-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": ["ecs-tasks.amazonaws.com"]
      },
      "Effect": "Allow"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "ecs_task_policy" {
  name   = "IAM-PLC-ECS-TASK-${var.prefix_name}"
  role   = aws_iam_role.ecs_task_role.name
  policy = data.template_file.ecs_task_role_policy.rendered
}

resource "aws_iam_role" "ecs_role" {
  name               = "IAM-ECS-${var.prefix_name}"
  assume_role_policy = file("${path.module}/policies/ecs-assume-role.json")
}

resource "aws_iam_role_policy" "ecs_role_ebs_policy" {
  name   = "IAM-PLC-ECS-EBS-${var.prefix_name}"
  role   = aws_iam_role.ecs_role.name
  policy = data.template_file.ecs_role_ebs.rendered
}

resource "aws_iam_role_policy_attachment" "ssm_session_manager_policy_attach" {
  role       = aws_iam_role.ecs_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonEC2RoleforSSM"
}

resource "aws_iam_role_policy_attachment" "ecs_service_policy_attach" {
  role       = aws_iam_role.ecs_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceRole"
}

resource "aws_iam_role_policy_attachment" "ecs_ec2_policy_attach" {
  role       = aws_iam_role.ecs_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceforEC2Role"
}

resource "aws_iam_role_policy_attachment" "ecr_policy_attach" {
  role       = aws_iam_role.ecs_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryPowerUser"
}

resource "aws_iam_instance_profile" "ecs_instance_profile" {
  name = "ecsInstanceProfileFor${var.prefix_name}"
  path = "/"
  role = aws_iam_role.ecs_role.name
}
