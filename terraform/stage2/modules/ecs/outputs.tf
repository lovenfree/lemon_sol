output "ecs_url" {
  value       = "https://${var.ecs_hostname}"
  description = "This is the URL to access Server"
}

output "ecs_cluster_id" {
  value       = aws_ecs_cluster.ecs_cluster.id
  description = "This is id for ECS cluster."
}