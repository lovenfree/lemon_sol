output "ecs_url" {
  value       = data.aws_alb.ECS_LOAD_BALANCER_EXTERNAL.dns_name
  description = "This is the URL to access Server"
}

output "ecs_cluster_id" {
  value       = aws_ecs_cluster.ecs_cluster.id
  description = "This is id for ECS cluster."
}
