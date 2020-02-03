#!groovy
import groovy.transform.Field

library ('PipelineUtils@develop') // This requires the snippet to be configured in the Jenkins server settings
env.TERRAFORM_VERSION = "0.12.18"
env.environment    // This will always start as dev and then change as the pipeline progresses
env.role_arn
env.application_account_id
// env.branch            = BRANCH_NAME.replaceAll('/','-').toLowerCase()
env.branch
env.build_id
env.slack_channel     = '#jenkinspipeline' // SETUP: This must be a valid slack channel for the configured Slack 
env.service_name     // defined here and set once we've checked out source
env.PREFIXNAME
env.DOMAINURL
env.REPOSITORY_NAME
env.submitter = 'song,mgch,jgikim,tkay369,dongjin1005'

pipeline {
  agent { node { label 'ecs-slaves' } }
  options {
    ansiColor('xterm')
    timestamps()
    timeout(time: 24, unit: 'HOURS')
    // disableConcurrentBuilds() 
  }
  post {
    always {
      script {
        notifyBitbucket()
        try { sh ('cat /home/jenkins/.npm/_logs/*.log 2> /dev/null') } catch(e) { }
        build_status =  currentBuild.result
        echo "build_status = " + build_status

        subject = "${build_status}: Job '${env.JOB_NAME} [Build ${env.BUILD_NUMBER}]'"
        summary = "${subject} (${env.BUILD_URL})"

        if (build_status == 'SUCCESS') {
          colorCode = '#00FF00'       // GREEN
        } else {
          colorCode = '#FF0000'       // RED
        }
        if (BRANCH_NAME == 'develop' || BRANCH_NAME == 'master') {
          slackSend (channel: slack_channel, color: colorCode, message: summary)
        }
      }
    }
  }
  stages {
    stage("Git checkout") { steps { checkout scm } }
    
    stage("Gradle Build") {
      steps {
        sh "mkdir -p ~/.gradle && echo \"org.gradle.daemon=true\" >> ~/.gradle/gradle.properties"
        sh "cat ~/.gradle/gradle.properties"
        sh "./gradlew clean build -x test -x unitTest -x moduleTest"
      }
    }

    stage("When feature/PR/develop Branch") {
      when { expression { BRANCH_NAME != 'master' } }
      stages {
        stage("[DEV] Set Dev environment") {
          steps {
            script {
              env.environment        = 'dev'
              env.application_account_id = '754860697973'
              env.role_arn           = 'arn:aws:iam::754860697973:role/IAM-DEV-JKS-SLV-AssumeRole'
              env.DOMAINURL          = "intf.vportaldev.koreabuild.com"
              env.build_id           = sh ( script: 'bash ./cicd/bash/get-current-branch.sh', returnStdout: true ).trim()

              env.service_base_name  = sh ( script: 'grep rootProject.name settings.gradle', returnStdout: true ).trim()
              env.service_name       = service_base_name.split('=')[1].trim().replace('"', "").replace('\'', "")

              env.base_version_string  = sh( script: "grep ^version gradle.properties", returnStdout: true )
              env.base_version_info    = base_version_string.tokenize('=')[1].replace('"', "").tokenize('.')
              env.base_version         = "${base_version_info[0].trim()}.${base_version_info[1].trim()}"
              env.git_number_of_commit = sh( script: 'git rev-list HEAD --count', returnStdout: true ).trim()
              env.version              = "${base_version}.${git_number_of_commit}"
              
              env.PREFIXNAME      = "${service_name}-${build_id}"
              env.REPOSITORY_NAME = "${PREFIXNAME}"
              if (BRANCH_NAME != 'develop') {
                env.DOMAINURL = "${PREFIXNAME}-${DOMAINURL}"
              }
              echo "****** Setting service_name to ${service_name}"
              echo "****** Setting PREFIXNAME to ${PREFIXNAME}"
              echo "****** Setting DOMAINURL to ${DOMAINURL}"
            }
          }
        }
        
        stage("Check ECR Repository") {
          steps {
            awsAssumeRole(role_arn)
            script {
              env.doesECRexist = sh ( script: "aws ecr describe-repositories | jq '.repositories[].repositoryName' | grep ${PREFIXNAME}.\$ | wc -l", returnStdout: true ).trim()
              if (doesECRexist.equals('0')) {
                println "Creating a new ECR repository for [${service_name}]"
                sh(script: "aws ecr create-repository --repository-name ${REPOSITORY_NAME}", returnStdout: true).trim()
              }
            }

            sh("sed -i -e s/ACCOUNT_ID/${application_account_id}/g ecr_policy.json")
            sh(script: "aws ecr set-repository-policy --repository-name ${REPOSITORY_NAME} --policy-text file://ecr_policy.json") 
          }
        }

        stage("Build Docker Image") {
          steps {
            script {
              env.ECRURI = sh ( script: "aws ecr describe-repositories | jq '.repositories[].repositoryUri' | grep ${PREFIXNAME}.\$", returnStdout: true ).trim()
            }
            sh("sed -i -e s/_ECRURI_/${ECRURI.replace('/', '\\/')}/g build.gradle")
            // build docker image and upload to ECR
            sh("AWS_REGION=ap-northeast-2 DOCKER_CONFIG=docker_config_ecr.json ./gradlew jib -Djib.to.tags=latest --info")
          }
        }

        stage("Deploy terraform stage1 : common Infra") {
          steps {
            sh("""
              pwd
              wget -q https://releases.hashicorp.com/terraform/${TERRAFORM_VERSION}/terraform_${TERRAFORM_VERSION}_linux_amd64.zip
              unzip terraform_${TERRAFORM_VERSION}_linux_amd64.zip -d /home/jenkins/bin
              chmod 755 /home/jenkins/bin/*
              terraform version
            """)

            sh("aws sts get-caller-identity")
            sh("chmod 744 ./terraform/scripts/setenv")
            sh("cd ./terraform/stage1; chmod 744 get-setenv.sh")
            sh("cd ./terraform/stage1; ./get-setenv.sh configurations/dev-config.tfvar")
            sh("cd ./terraform/stage1; terraform apply -auto-approve -var-file configurations/dev-config.tfvar")
          }
        }

        stage("Deploy terraform stage2 : each branch Infra") {
          steps {
            script {
              env.ECRNAME = sh(script: "aws ecr describe-repositories | jq '.repositories[].repositoryName' | grep ${PREFIXNAME}.\$", returnStdout: true).trim()
              env.ECS_LOAD_BALANCER_EXTERNAL_NAME = sh(returnStdout: true, script: "aws ssm get-parameter --name '/V-P-I/ECS_LOAD_BALANCER_EXTERNAL_NAME' --with-decryption --query 'Parameter.Value' | sed 's#\"##g'").trim()
              env.ECS_ALB_LISTENER_ARN = sh(returnStdout: true, script: "aws ssm get-parameter --name '/V-P-I/ECS_ALB_LISTENER_ARN' --with-decryption --query 'Parameter.Value'").trim()
              env.ECS_SG_INTERNAL_ID = sh(returnStdout: true, script: "aws ssm get-parameter --name '/V-P-I/ECS_SG_INTERNAL_ID' --with-decryption --query 'Parameter.Value' | sed 's#\"##g'").trim()
              env.ECRURI = sh(script: "aws ecr describe-repositories | jq '.repositories[].repositoryUri' | grep ${PREFIXNAME}.\$", returnStdout: true).trim()
            }
            sh ("""
              sed -i -e s/_PREFIXNAME_/${PREFIXNAME}/g terraform/stage2/configurations/${environment}-config.tfvar
              sed -i -e s/_DOMAINURL_/${DOMAINURL}/g terraform/stage2/configurations/${environment}-config.tfvar
              sed -i -e s/_ECRURI_/${ECRURI.replace('/', '\\/')}/g terraform/stage2/configurations/${environment}-config.tfvar
              sed -i -e s/_ECS_LOAD_BALANCER_EXTERNAL_NAME_/${ECS_LOAD_BALANCER_EXTERNAL_NAME}/g terraform/stage2/configurations/${environment}-config.tfvar
              sed -i -e s/_ECS_ALB_LISTENER_ARN_/${ECS_ALB_LISTENER_ARN.replace('/', '\\/')}/g terraform/stage2/configurations/${environment}-config.tfvar
              sed -i -e s/_ECS_SG_INTERNAL_ID_/${ECS_SG_INTERNAL_ID}/g terraform/stage2/configurations/${environment}-config.tfvar
              echo '=== ${environment}-config.tfvar ==='
              cat terraform/stage2/configurations/${environment}-config.tfvar
              echo '===================================='
            """)

            sh ("cd ./terraform/stage2; chmod 744 get-setenv.sh")
            sh ("cd ./terraform/stage2; ./get-setenv.sh configurations/dev-config.tfvar")
            // sh ("cd ./terraform/stage2; terraform destroy -auto-approve -var-file configurations/dev-config.tfvar")
            sh ("cd ./terraform/stage2; terraform apply -auto-approve -var-file configurations/dev-config.tfvar")
          }
        }
      }
    }
  }
}