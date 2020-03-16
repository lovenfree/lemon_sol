# Repo for Cloud automation source code


#### AWS Accounts

shared-services - 941225905618 - BUILDSHARED@lgcns.com  https://941225905618.signin.aws.amazon.com/console

##### To Run terraform against master account

- Get credentials

```
# Add below entry to ~/.aws/credentials file

[lg-master]
aws_access_key_id=--OMITTED--
aws_secret_access_key=--OMITTED--
aws_region=ap-northeast-2

source ./scripts/set-aws-profile.sh lg-master
```

- Assume role for member accounts

```
#Below is example to assume the role for logging account
source ./scripts/set-iam-crossaccount-role.sh lg-master OrganizationAccountAccessRole 551494876377
```
- Terraform init

```
# Run terraform against `master-init` stack
source get-setenv.sh configurations/master.tf

```

- Run terraform plan

```
terraform plan -var-file $DATAFILE
```

- Terraform apply

```
terraform apply -var-file $DATAFILE
```

##### TODO

- Create python script for oganization init
- Automate running cloudformation stackset against an account.