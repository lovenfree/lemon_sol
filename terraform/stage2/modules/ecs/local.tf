locals {
  cluster_name   = "ECS-${var.prefix_name}"
  container_name = "${var.prefix_name}_container"
  volume_name    = "${var.prefix_name}_BA_REPO_volume"
}