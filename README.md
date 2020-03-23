# Backend - SpringBoot

## Prerequisite

- [install git](https://git-scm.com/downloads)
- [install terraform](https://www.terraform.io/downloads.html)
- [create AWS access key & security key](https://docs.aws.amazon.com/ko_kr/IAM/latest/UserGuide/id_credentials_access-keys.html#Using_CreateAccessKey_CLIAPI)
- [install aws cli](https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/install-cliv2.html)
- [configure aws cli](https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/cli-chap-configure.html)
- [install Amazon ECR Docker Credential Helper](https://github.com/awslabs/amazon-ecr-credential-helper)
- [install JDK](https://github.com/ojdkbuild/ojdkbuild)

## Download source

- Bitbucket URL : [backend-spring Repository](https://wire.lgcns.com/bitbucket/projects/LGSTATION/repos/backend-spring/browse)
- git clone
  git clone 명령을 사용하여 로컬에 다운로드

    ```bash
    git clone https://wire.lgcns.com/bitbucket/scm/lgstation/backend-spring.git
    ```

- [git clone 사용방법](https://www.atlassian.com/git/tutorials/setting-up-a-repository/git-clone)

## ECR & Image

### create ECR(AWS CLI)

aws cli를 통해 ECR 을 생성한다.

```bash
aws ecr create-repository --repository-name ecr_springboot
```

### configure JIB

위에서 생성한 ECR_URI를 조회한다.
ECR_URI의 값은 아래의 AWS CLI명령을 통해서 조회가 가능하고, repositoryUri 항목의 값을 사용하면 된다.

```bash
aws ecr describe-repositories
```

ECR_URI를 parameter store에 put을 해야 한다. ${ECR_URI} 부분을 위에서 조회한 값으로 변경 후 실행한다.

```bash
aws ssm put-parameter --name "/V-P-I/ECR-URI" --type "SecureString" --value "${ECR_URI}" --overwrite
```

### create Image Using JIB

JIB 를 사용하여 Spring Boot 소스를 Docker Image로 만들어 ECR 에 업로드를 하게 된다.
위에서 사용했던 ECR_URI를 파라미터로 주고 아래의 명령을 실행한다.

```bash
AWS_REGION=ap-northeast-2 ./gradlew jib --info --image=${ECR_URI}
```

## create stage0(terraform)

기본 인프라를 생성하는 단계로 아래와 같은 Resource들을 생성함
(vpc, subnet, security group, ssm_parameter)

backend-spring/terraform/stage0 폴더내에서 아래의 명령을 실행

```bash
cd terraform/stage0
./get-setenv.sh configurations/VPORTALDEV.tfvars
terraform plan -var-file configurations/VPORTALDEV.tfvars
terraform apply -var-file configurations/VPORTALDEV.tfvars -auto-approve
```

생성된 VPC 등의 정보들은 [ssm parameter store](https://console.aws.amazon.com/systems-manager/parameters?region=us-east-1)에 저장 된다.

## create stage1(terraform)

ALB 관련 Resource들을 생성하는 단계로 브랜치별로 생성되어야 할 자원들과 분리하기 위해 단계가 나누어졌다.

```bash
cd terraform/stage1
./get-setenv.sh configurations/dev-config.tfvars
terraform plan -var-file configurations/dev-config.tfvars
terraform apply -var-file configurations/dev-config.tfvars -auto-approve
```

## create stage2(terraform)

ALB Listener Rule을 추가하고, ECS 관련 자원들을 생성하여 container 기동이 된다.

```bash
cd terraform/stage2
./get-setenv.sh configurations/dev-config.tfvars
terraform plan -var-file configurations/dev-config.tfvars
terraform apply -var-file configurations/dev-config.tfvars -auto-approve
```

terraform apply가 성공적으로 끝나도 정상적으로 container가 기동되려면 5~10분 정도 시간이 더 소요된다.
로그 마지막에 출력된 ECS_URL로 브라우저에서 접속해 볼 수 있다.

## Learn More

- [Terraform](https://www.terraform.io/intro/index.html)
- [JIB](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#quickstart)
- [AWS CLI](https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/cli-chap-using.html)
- [BuildCenter Workbook - Back-End (Container) 개발 가이드](https://wire.lgcns.com/confluence/pages/viewpage.action?pageId=35801685)