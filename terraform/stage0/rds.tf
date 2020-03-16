# for application rds(mariadb)
resource "random_password" "rds_password" {
  length  = 16
  special = false
}

# locals {
#   env = var.environment == "feature" ? "dev" : var.environment == "pr" ? "dev" : "${var.environment}"
# }
resource "aws_db_subnet_group" "rds_subnet" {
  name_prefix = "${var.db_name}_db_subnet"
  subnet_ids  = [module.vpc.private_subnets[0], module.vpc.private_subnets[1]]
}

resource "aws_db_instance" "rds_instance" {
  allocated_storage      = var.allocated_storage
  storage_type           = var.storage_type
  engine                 = var.engine
  engine_version         = var.engine_version
  instance_class         = var.instance_class
  identifier_prefix      = var.instance_name_prefix
  name                   = var.db_name
  username               = var.username
  password               = random_password.rds_password.result
  port                   = var.port
  db_subnet_group_name   = aws_db_subnet_group.rds_subnet.id
  publicly_accessible    = var.publicly_accessible
  storage_encrypted      = var.storage_encrypted
  skip_final_snapshot    = var.skip_final_snapshot
  vpc_security_group_ids = [aws_security_group.db_security_group.id, ]
  # maintenance_window = ""
  # deletion_protection = ""
  # max_allocated_storage = ""
  # monitoring_interval = ""
  # multi_az = ""
}

# resource "aws_db_snapshot" "rds_snap" {
#   db_instance_identifier = "${aws_db_instance.rds_instance}"
#   db_snapshot_identifier = "snapshot-${var.instance_name_prefix}"
# }

resource "aws_security_group" "db_security_group" {
  name   = "SG-${var.db_name}"
  vpc_id = module.vpc.vpc_id

  ingress {
    protocol        = "tcp"
    from_port       = var.port
    to_port         = var.port
    security_groups = [aws_security_group.lambda_security_group.id, ]
    //  cidr_blocks = "${var.vpc_cidr_lists}"
  }

  ingress {
    protocol    = "tcp"
    from_port   = var.port
    to_port     = var.port
    cidr_blocks = ["10.244.5.0/25"]
  }

  ingress {
    protocol    = "tcp"
    from_port   = var.port
    to_port     = var.port
    cidr_blocks = [var.db_access_controle_cidr]
    description = "DB_Access_Control"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_ssm_parameter" "rds_endpoint" {
  name  = "/${var.environment}/database/RDSPARAMETER/endpoint"
  type  = "SecureString"
  value = aws_db_instance.rds_instance.endpoint
}

resource "aws_ssm_parameter" "rds_password" {
  name  = "/${var.environment}/database/RDSPARAMETER/password"
  type  = "SecureString"
  value = random_password.rds_password.result
}
