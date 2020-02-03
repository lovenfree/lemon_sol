# Verdaccio ECS Module

Terraform module which creates Verdaccio ECS resources on AWS.

## Usage

```hcl
module "verdaccio" {
  source                                      = "../../../modules/verdaccio"
  region                                      = var.primary_region
  vpc_cidr                                    = var.VPC_CIDRS["SHARED_SERVICES"]
  ecs-instance-ami                            = var.ecs-instance-ami
  ecs_hostname                          = var.ecs_hostname
  vpc_id                                      = module.shared_services.vpc_id
  aws_route53_zone_tools_zone_id              = module.shared_services.aws_route53_zone_tools_zone_id
  jenkins_ecs_load_balancer_external_dns_name = module.shared_services.jenkins_ecs_load_balancer_external_dns_name
  jenkins_ecs_load_balancer_external_zone_id  = module.shared_services.jenkins_ecs_load_balancer_external_zone_id
  jenkins_ecs_load_balancer_external_arn      = module.shared_services.jenkins_ecs_load_balancer_external_arn
}
```

## Optional Variables

- **region** - Name of the region
- **vpc_id** - VPC ID to set security group
- **vpc_cidr** - VPC CIDR to set security group
- **ecs-instance-ami** - ec2 ami for verdaccio ECS instance
- **KeyPairName** - keypair for verdaccio ECS instance
- **ecs_hostname** - This hostname is for verdaccio. It route to verdaccio ECS Task
- **sharedservices_private_subnet_1_id** - This is for verdaccio autoscalegroup
- **sharedservices_private_subnet_2_id** - This is for verdaccio autoscalegroup
- **aws_route53_zone_tools_zone_id** - route53 zone id for createing verdaccio A record
- **jenkins_ecs_load_balancer_external_dns_name** - This set ALB aliase, when creating route53 A record for verdaccio.
- **jenkins_ecs_load_balancer_external_zone_id** - This set ALB aliase, when creating route53 A record for verdaccio.
- **jenkins_ecs_load_balancer_external_arn** - This is ALB arn for verdaccio.

## Outputs

- **ecs_url** - This is the URL to access Server
- **ecs_cluster_id** - This is id for ECS cluster
