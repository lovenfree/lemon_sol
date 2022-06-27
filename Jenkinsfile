#!groovy
library ('PipelineUtils@develop') // This requires the snippet to be configured in the Jenkins server settings
def isFeatureBranch = !(BRANCH_NAME == 'develop' || BRANCH_NAME == 'stage' || BRANCH_NAME == 'master')
def isDevelopBranch = BRANCH_NAME == 'develop'
def build_id      = env.build_id
// env.slack_workspace = 'lgcns-mi'         // Slack workspace address
// env.slack_token_id  = 'lgcns-mip-slack'
// def slack_channel = '#jenkinspipeline' // SETUP: This must be a valid slack channel for the configured Slack integration
// def slack_channel_smoke = '#jenkinspipeline_smoke' // SETUP: This must be a valid slack channel for the configured Slack integration

env.environment    // This will always start as dev and then change as the pipeline progresses
env.BRANCH_NAME = BRANCH_NAME
env.submitter = 'hun_k, hsj5009'

def getContainerName(isFeatureBranch, service_name, branch, environment){
    def name = ""

    if (isFeatureBranch){
        name = service_name + "-" + branch
    } else {
        name = service_name + "-" + environment
    }
    return name
}

pipeline {
    agent {
        kubernetes {
            inheritFrom 'did-infra' // agent image 기입
        }
    }
    options {
        ansiColor('xterm')
        timestamps()
        timeout(time: 24, unit: 'HOURS')
        disableConcurrentBuilds()
        skipDefaultCheckout()
    }
    // post {
    //     always {
    //         script {
    //             notifyBitbucket()
    //             if (BRANCH_NAME == 'develop' || BRANCH_NAME == 'develop2' || BRANCH_NAME == 'master') {
    //                 notifySlack(slack_workspace, slack_token_id, slack_channel, currentBuild.result)
    //             }
    //             echo "domain url: " + env.DOMAINURL
    //         }
    //     }
    // }
    environment {
        SVC_ACCOUNT_DEV_KEY = credentials('did-dev')
        SVC_ACCOUNT_STG_KEY = credentials('did-stg')
        SVC_ACCOUNT_PRD_KEY = credentials('did-prd')

        GOOGLE_APPLICATION_CREDENTIALS = "${WORKSPACE}/creds/serviceaccount.json"
    }
    stages {
        stage("Git checkout") {
            steps {
                checkout scm
            }
        }

        stage("Environment") {
            steps {
                script {
                    env.REGION          = "asia-northeast3"
                    env.service_name = "solution-backend"
                  // env.branch          = sh ( script: 'bash ./cicd/script/get-current-branch.sh', returnStdout: true ).trim()
                  //   env.service_name    = sh ( script: 'grep rootProject.name "./settings.gradle" | cut -d= -f2 | sed -e "s#\'##g"', returnStdout: true ).trim()
                }
            }
        }

        stage("Common Setup") {
            steps {
                script {
                    ///////////////////////// install java //////////////////////////
                    sh 'apk add openjdk17 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community'
                    env.JAVA_HOME="/usr/lib/jvm/java-17-openjdk"
                    env.PATH="/usr/lib/jvm/java-17-openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/lib/google-cloud-sdk/bin"
                    sh 'printenv'
                    sh 'java -version'
                    /////////////////////////////////////////////////////////////////
                    // install gcloud

                    sh '''
                    echo "install gcloud"
                    cat /etc/os-rel*

                    export PATH="${PATH}:/home/gcloud/bin:/home/gcloud/google-cloud-sdk/bin"
                    apk update && apk upgrade
                    apk add python2
                    export CLOUDSDK_PYTHON=python2
                    mkdir /home/gcloud

                    wget -q https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-388.0.0-linux-x86_64.tar.gz \
                    && tar -xf ./google-cloud-sdk-388.0.0-linux-x86_64.tar.gz -C /home/gcloud \
                    && rm -rf ./google-cloud-sdk-388.0.0-linux-x86_64.tar. \
                    && gcloud components install beta --quiet \
                    && gcloud components install cbt --quiet '''
                    sh ' echo "gcloud install complete"'

                }
            }
        }

        stage('Develop Branch') {
            when { expression { BRANCH_NAME == 'develop'}}
            stages {
                stage("[DEV] Environment") {
                    steps {
                        script {
                            env.environment         = 'dev'
                            env.project_id          = 'pjt-did-dev'
                           //  env.PORT_NO             = sh(script: "grep -A1 server src/main/resources/application.yml | grep port | grep -v 'server-port' | awk '{print \$2}'", returnStdout: true).trim()

                           //  if (isFeatureBranch){
                           //      env.DOMAINURL = env.branch + ".be-" + env.environment + "." + env.DOMAINURL
                           //      env.namespace = env.service_name + "-" + env.branch
                           //      env.ksa_name  = env.service_name + "-" + env.branch
                           //      env.PROFILES  = "feature"
                           //      env.DB_SCHEME = "mi-" + env.branch
                           //      env.DB_SCHEME_F = "\\`mi-" + env.branch + "\\`"
                           //      env.pdb = '0'
                           //      sh 'sed -i -e \"s#_IS_FEATURE_#Y#g\" cicd/terraform/configurations/${environment}.tfvars'
                           //  }else{
                           //      env.DOMAINURL = "be-" + env.environment + "." + env.DOMAINURL
                           //      env.namespace = env.service_name + "-" + env.environment
                           //      env.ksa_name = env.service_name + "-" + env.environment
                           //      env.PROFILES   = "dev"
                           //      env.DB_SCHEME = "mi"
                           //      env.DB_SCHEME_F = "\\`mi\\`"
                           //      env.pdb = '1'
                           //  }
                           //  sh ("echo 'DomainURL is :   ' ${DOMAINURL}")

                            // make gcp credentials

                            sh 'mkdir -p creds'
                            sh 'echo $SVC_ACCOUNT_DEV_KEY | base64 -d > $GOOGLE_APPLICATION_CREDENTIALS'
                            sh 'echo $GOOGLE_APPLICATION_CREDENTIALS'

                            // gcloud init
                            sh 'gcloud auth activate-service-account --key-file ${GOOGLE_APPLICATION_CREDENTIALS}'
                            sh 'gcloud auth configure-docker'
                            sh 'gcloud config set project ${project_id}'

                           //  // store-pass
                           //  def storepass = sh ( script: 'gcloud secrets versions access 1 --secret sso_storepass_secrets', returnStdout: true).trim()
                           //  sh ("sed -i s#STOREPASS#${storepass}#g src/main/resources/application*.yml")
                           //  sh ("grep 'store-pass' src/main/resources/application-dev.yml")

                           //  // sso key
                           //  sh 'gcloud secrets versions access 4 --secret sso_private_key_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/mi_dev.jks'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lgcns_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgcns.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lge_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lge.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lgcare_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgcare.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lgchem_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgchem.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lgdisplay_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgdisplay.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lgensol_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgensol.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lginnotek_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lginnotek.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lguplus_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lguplus.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lxhausys_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lxhausys.xml'
                           //  sh 'gcloud secrets versions access 1 --secret sso_lxsemicon_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lxsemicon.xml'

                           //  // kms
                           //  sh 'gcloud secrets versions access 1 --secret mysql8_data_encrypt_key_secrets > src/main/resources/security/db-data-encrypt-dev.json'

                           //  sh 'ls -ls src/main/resources/security'
                        }
                    }
                }

               //  stage("Static Analysis(SonarQube) & Unit Test") {
               //      // cf: https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-jenkins/
               //      // when {branch 'develop'}
               //      steps {
               //          withSonarQubeEnv('mip-sonarqube') {
               //              sh '''./gradlew sonarqube -Dsonar.projectKey=${service_name}
               //              '''
               //          }
               //      }
               //  }

               //  stage("[DEV] Apply Terraform"){
               //      steps {
               //          dir('cicd/terraform'){
               //              script {
               //                  sh '''
               //                  sed -i -e \"s#_BRANCH_NAME_#${branch}#g\" configurations/${environment}.tfvars
               //                  sed -i -e \"s#_NAMESPACE_#${namespace}#g\" configurations/${environment}.tfvars
               //                  sed -i -e \"s#_KSA_NAME_#${ksa_name}#g\" configurations/${environment}.tfvars
               //                  '''
               //                  sh '''
               //                  chmod +x get-setenv.sh
               //                  ./get-setenv.sh configurations/${environment}.tfvars
               //                  terraform apply -auto-approve -var-file configurations/${environment}.tfvars
               //                  '''
               //                  env.service_account_email = sh ( script: 'terraform output service_account_email', returnStdout: true ).trim()
               //              }
               //          }
               //      }
               //  }

                // build Docker Image
                // cf: https://cloud.google.com/java/getting-started/jib?hl=ko
                //     https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin
                // stage("[DEV] NPM lint") { steps { sh "${npm_lint}" } }
                // stage("[DEV] NPM unit test") { steps { sh "${npm_unit_test}" } }
                // stage("[DEV] Build Project") { steps { sh ("bash ./cicd/bash/build.sh $releaseVersion $environment") } }
                stage("[DEV] Build Docker Image") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                           //  env.containerName   = getContainerName(isFeatureBranch, service_name, branch, environment)
                           //  env.dbpassword = sh ( script: 'gcloud secrets versions access 1 --secret=mysql8_root_secrets', returnStdout: true).trim()
                           //  env.googleApikey = sh ( script: 'gcloud secrets versions access 1 --secret=google_api_secrets', returnStdout: true).trim()
                           //  env.cseApikey = sh ( script: 'gcloud secrets versions access 1 --secret=cse_api_secrets', returnStdout: true).trim()
                           //  env.youtubeApikey = sh ( script: 'gcloud secrets versions access 1 --secret=youtube_api_secrets', returnStdout: true).trim()
                           //  env.tableauAdminUser = sh ( script: 'gcloud secrets versions access 1 --secret=tableau_admin_user_secrets', returnStdout: true).trim()
                           //  env.tableauAdminPassword = sh ( script: 'gcloud secrets versions access 1 --secret=tableau_admin_pw_secrets', returnStdout: true).trim()
                           //  env.esAdminUser = sh ( script: 'gcloud secrets versions access 1 --secret=es_admin_user_secrets', returnStdout: true).trim()
                           //  env.esAdminPassword = sh ( script: 'gcloud secrets versions access 1 --secret=es_admin_pw_secrets', returnStdout: true).trim()
                           //  env.apimApikey = sh ( script: 'gcloud secrets versions access 1 --secret=apim_api_secrets', returnStdout: true).trim()
                           //  env.lvSecretkey = sh ( script: 'gcloud secrets versions access 1 --secret=lvi_key_secrets', returnStdout: true).trim()
                           //  env.Redis_IP = sh ( script: 'gcloud redis instances list --region ${REGION} --filter=name:projects/${project_id}/locations/${REGION}/instances/redis-an3-mip-${environment}-session-01 --format="value(host)"', returnStdout: true).trim()
                           //  sh ("echo 'container name is :   ' ${containerName}")
                           //  sh '''
                           //  VERSION=2.0.0
                           //  OS=linux  # or "darwin" for OSX, "windows" for Windows.
                           //  ARCH=amd64  # or "386" for 32-bit OSs, "arm64" for ARM 64.
                           //  curl -fsSL "https://github.com/GoogleCloudPlatform/docker-credential-gcr/releases/download/v${VERSION}/docker-credential-gcr_${OS}_${ARCH}-${VERSION}.tar.gz" | tar xz --to-stdout ./docker-credential-gcr > /usr/local/bin/docker-credential-gcr && chmod +x /usr/local/bin/docker-credential-gcr
                           //  '''
                           //  sh '''
                           //  sed -i -e s#_TAG_#${BUILD_NUMBER}#g build.gradle
                           //  set +x
                           //  sed -i s#MI_SCHEME#${DB_SCHEME}#g src/main/resources/application.yml
                           //  sed -i s#redisHost#${Redis_IP}#g src/main/resources/application.yml
                           //  sed -i s#appuser#beappuser#g src/main/resources/application.yml
                           //  sed -i s#GOOGLE_API_KEY#${googleApikey}#g src/main/resources/application.yml
                           //  sed -i s#CSE_API_KEY#${cseApikey}#g src/main/resources/application.yml
                           //  sed -i s#YOUTUBE_API_KEY#${youtubeApikey}#g src/main/resources/application.yml
                           //  sed -i s#TABLEAU_ADMIN_USER#${tableauAdminUser}#g src/main/resources/application.yml
                           //  sed -i s#TABLEAU_ADMIN_PASSWD#${tableauAdminPassword}#g src/main/resources/application.yml
                           //  sed -i s#ES_ADMIN_USER#${esAdminUser}#g src/main/resources/application.yml
                           //  sed -i s#ES_ADMIN_PASSWD#${esAdminPassword}#g src/main/resources/application.yml
                           //  sed -i s#APIM_API_KEY#${apimApikey}#g src/main/resources/application.yml
                           //  sed -i s#LV_SECRET_KEY#${lvSecretkey}#g src/main/resources/application.yml
                           //  set -x
                           //  '''
                           //  // userSql = 'V0_0_000_0__Initial_DB_User.sql'
                           //  userSql = 'src/main/resources/flyway/common_sql/V0_0_000_0__Initial_DB_User.sql'
                           //  userList = [ 'beappuser', 'batchuser', 'functionuser', 'viewer', 'tableau_viewer' ]
                           //  for (item in userList) {
                           //      userPassword = sh ( script: "gcloud secrets versions access 1 --secret=mysql8_${item}_secrets", returnStdout: true).trim()
                           //      if (item == 'viewer') {
                           //          createDBUserType(userSql, item, userPassword, DB_SCHEME_F, 'select')
                           //      } else {
                           //          createDBUserType(userSql, item, userPassword, DB_SCHEME_F, 'CRUD')
                           //      }
                           //      if (item == 'beappuser') {
                           //          sh '''
                           //          set +x
                           //          userPassword=`gcloud secrets versions access 1 --secret=mysql8_beappuser_secrets`
                           //          set -x
                           //          '''
                           //          env.appPassword = sh ( script: "echo -n ${userPassword} | base64 ", returnStdout: true).trim()
                           //      }
                           //  }
                            // sh "cat ${userSql}"
                            env.DOCKERIMG = "asia.gcr.io/" + env.project_id + "/" + env.BRANCH_NAME + "/" + env.service_name + ":" + env.BUILD_NUMBER

                           sh './gradlew jib --image='+env.DOCKERIMG

                        }
                    }
                }
                stage("[DEV] Deploy Backend Pod") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                            // sh '''
                            //     PROJECTID=$(grep project_id  ./configurations/did-dev.tfvars | awk -F \'=\' \'{print $2}\' | sed -e \'s/[" ]//g\')
                            //     REGION=$(grep region ./configurations/did-dev.tfvars | grep -v "/" |awk -F \'=\' \'{print $2}\' | sed -e \'s/[" ]//g\')
                            //     CLUSTER_NAME=`cat ./configurations/did-dev.tfvars | grep cluster_name | awk -F \'=\' \'{print $2}\' | sed -e \'s/[\" ]//g\'`
                            //     gcloud container clusters get-credentials $CLUSTER_NAME --region $REGION --project $PROJECTID
                            //     gcloud components install kubectl
                            //     '''
                            sh ("sed -i -e s%_SERVICEIMG_%$DOCKERIMG%g ./k8/stage-dev/deployment.yaml")
                            sh '''
                            PROJECTID=pjt-did-dev
                            REGION=asia-northeast3
                            CLUSTER_NAME=gke-an3-did-dev
                            gcloud container clusters get-credentials $CLUSTER_NAME --region $REGION --project $PROJECTID
                            gcloud components install kubectl
                            kubectl apply -f ./k8/stage-dev/
                            '''




                        }
                    }
                }
               //  stage("[DEV] GKE Deploy"){
               //      steps {
               //          script {
               //              env.fwPassword = sh ( script: "echo -n ${dbpassword} | base64 ", returnStdout: true).trim()
               //              sh '''
               //              CLUSTER_NAME="gke-an3-mip-${environment}-gke-cluster-01"
               //              gcloud container clusters get-credentials ${CLUSTER_NAME} --region $REGION --project ${project_id}
               //              '''
               //              sh '''
               //              DBConnectionName=`gcloud sql instances describe gdb-an3-mip-${environment}-mysql8-01 --format="value(connectionName)" --project ${project_id}`
               //              DBPort="3306"
               //              sed -i -e s#_RELEASE_VERSION_#${BUILD_NUMBER}#g cicd/charts/Chart.yaml
               //              sed -i "s/_PORT_NO_/${PORT_NO}/g" cicd/charts/templates/deployment.yaml
               //              sed -i "s/_SERVICE_ACCOUNT_/${service_account_email}/g" cicd/charts/values.yaml
               //              helm upgrade --install --namespace ${namespace} \
               //                           --set serviceAccount.name=${ksa_name} \
               //                           --set sqlProxy.INSTANCE_CONNECTION_NAME=${DBConnectionName} --set sqlProxy.DB_PORT=${DBPort} \
               //                           --set image.repository=gcr.io/${project_id}/${containerName} --set image.tag=${BUILD_NUMBER} \
               //                           --set service.port=${PORT_NO} --set ingress.hosts[0].host=${DOMAINURL} --set ingress.hosts[0].paths={'/'} \
               //                           --set envVariables.profiles=${PROFILES} --set pdbMinAvailable=${pdb} \
               //                           --set envVariables.DBpwd=${fwPassword} --set envVariables.appPassword=${appPassword} \
               //                           --create-namespace --debug --wait ${namespace} ./cicd/charts
               //              '''
               //          }
               //          timeout(time: 3, unit: 'MINUTES') {
               //              script {
               //                  desiredReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.spec.replicas}'", returnStdout: true ).trim()
               //                  readyReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.status.readyReplicas}'", returnStdout: true ).trim()
               //                  while(desiredReplicas != readyReplicas) {
               //                      readyReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.status.readyReplicas}'", returnStdout: true ).trim()
               //                      sleep(15)
               //                  }
               //              }
               //          }
               //      }
               //  }
               //  stage('[DEV] Flyway') {
               //      steps{
               //          script {
               //              dnsCount = sh ( script: "dig -t txt ${DOMAINURL} | grep ${DOMAINURL} | wc -l", returnStdout: true ).trim()
               //              while(dnsCount != "3") {
               //                  dnsCount = sh ( script: "dig -t txt ${DOMAINURL} | grep ${DOMAINURL} | wc -l", returnStdout: true ).trim()
               //                  sleep(15)
               //              }
               //              sh "ls -la"
               //              sh "sed -i -e s/DomainURL/${DOMAINURL}/g api-tests/postman_environment/MIBE-Environment-feature.postman_environment.json"
               //              sh '''
               //              for collection in api-tests/flyway/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-feature.postman_environment.json; done
               //              '''
               //          }
               //      }
               //  }
               //  stage('[DEV] NPM module test') {
               //      when { expression { isFeatureBranch } }
               //      steps {
               //          //def statusCode = sh script:script, returnStatus:false
               //          script {
               //              // sh "dig -v"   // check whether dig has been installed
               //              // sh "apt-get install -y dnsutils"    // install dig
               //              dnsCount = sh ( script: "dig -t txt ${DOMAINURL} | grep ${DOMAINURL} | wc -l", returnStdout: true ).trim()
               //              while(dnsCount != "3") {
               //                  dnsCount = sh ( script: "dig -t txt ${DOMAINURL} | grep ${DOMAINURL} | wc -l", returnStdout: true ).trim()
               //                  sleep(15)
               //              }
               //              sh "ls -la"
               //              sh "sed -i -e s/DomainURL/${DOMAINURL}/g api-tests/postman_environment/MIBE-Environment-feature.postman_environment.json"
               //              sh '''
               //                  for collection in api-tests/module/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-feature.postman_environment.json; done
               //              '''
               //          }
               //      }
               //  }
               //  stage('[DEV] NPM ES module test') {
               //      when { expression { isFeatureBranch } }
               //      steps {
               //          //def statusCode = sh script:script, returnStatus:false
               //          script {
               //              // sh "dig -v"   // check whether dig has been installed
               //              // sh "apt-get install -y dnsutils"    // install dig
               //              dnsCount = sh ( script: "dig -t txt ${DOMAINURL} | grep ${DOMAINURL} | wc -l", returnStdout: true ).trim()
               //              while(dnsCount != "3") {
               //                  dnsCount = sh ( script: "dig -t txt ${DOMAINURL} | grep ${DOMAINURL} | wc -l", returnStdout: true ).trim()
               //                  sleep(15)
               //              }
               //              sh "ls -la"
               //              sh "sed -i -e s/DomainURL/${DOMAINURL}/g api-tests/postman_environment/MIBE-Environment-feature.postman_environment.json"
               //              sh '''
               //                  for collection in api-tests/smoke/*EsSearch*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-feature.postman_environment.json; done
               //              '''
               //          }
               //      }
               //  }

               //  stage("[DEV] Health Check"){
               //      when { expression { isDevelopBranch } }
               //      steps {
               //          script {
               //              sh '''
               //              for collection in api-tests/health/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-dev.postman_environment.json --insecure; done
               //              '''
               //          }
               //      }
               //  }

               //  stage("[DEV] Smoke Test") {
               //      when { expression { isDevelopBranch } }
               //      steps {
               //          script {
               //                  sh '''
               //                  for collection in api-tests/smoke/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-dev.postman_environment.json --insecure; done
               //                  '''
               //          }
               //      }
               //  }
            }
        }

        // stage('Master Branch') {
        //     when {branch 'master'}
        //     stages {
        //         stage("[Stage] Environment") {
        //             steps {
        //                 script {
        //                     env.environment         = 'stg'
        //                     env.project_id          = 'pjt-mip-stg-1'
        //                     env.PORT_NO             = sh(script: "grep -A1 server src/main/resources/application.yml | grep port | grep -v 'server-port' | awk '{print \$2}'", returnStdout: true).trim()

        //                     env.DOMAINURL = "be-" + env.environment + "." + env.DOMAINURL
        //                     env.namespace = env.service_name + "-" + env.environment        // be-stg
        //                     env.ksa_name = env.service_name + "-" + env.environment         // mi-be-stg
        //                     env.PROFILES   = "stage"
        //                     env.DB_SCHEME = "mi"
        //                     env.pdb = '1'
        //                     sh ("echo 'DomainURL is :   ' ${DOMAINURL}")

        //                     // make gcp credentials
        //                     sh 'mkdir -p creds'
        //                     sh 'echo $SVC_ACCOUNT_STG_KEY | base64 -d > $GOOGLE_APPLICATION_CREDENTIALS'

        //                     // gcloud init
        //                     sh 'gcloud auth activate-service-account --key-file ${GOOGLE_APPLICATION_CREDENTIALS}'
        //                     sh 'gcloud auth configure-docker'
        //                     sh 'gcloud config set project ${project_id}'

        //                     // store-pass
        //                     def storepass = sh ( script: 'gcloud secrets versions access 1 --secret sso_storepass_secrets', returnStdout: true).trim()
        //                     sh ("sed -i s#STOREPASS#${storepass}#g src/main/resources/application*.yml")
        //                     sh ("grep 'store-pass' src/main/resources/application-stage.yml")

        //                     // submitter
        //                     env.submitter = sh ( script: 'gcloud secrets versions access 1 --secret be_submitter_secrets', returnStdout: true).trim()

        //                     // sso key
        //                     sh 'gcloud secrets versions access 1 --secret sso_private_key_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/mi_stg.jks'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgcns_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgcns.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lge_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lge.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgcare_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgcare.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgchem_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgchem.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgdisplay_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgdisplay.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgensol_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgensol.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lginnotek_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lginnotek.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lguplus_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lguplus.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lxhausys_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lxhausys.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lxsemicon_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lxsemicon.xml'

        //                     // kms
        //                     sh 'gcloud secrets versions access 1 --secret mysql8_data_encrypt_key_secrets > src/main/resources/security/db-data-encrypt-stg.json'

        //                     sh 'ls -ls src/main/resources/security'
        //                 }
        //             }
        //         }

        //         stage("[Stage] Apply Terraform"){
        //             steps {
        //                 dir('cicd/terraform'){
        //                     script {
        //                         sh '''
        //                         sed -i -e \"s#_BRANCH_NAME_#${environment}#g\" configurations/${environment}.tfvars
        //                         sed -i -e \"s#_NAMESPACE_#${namespace}#g\" configurations/${environment}.tfvars
        //                         sed -i -e \"s#_KSA_NAME_#${ksa_name}#g\" configurations/${environment}.tfvars
        //                         '''
        //                         sh '''
        //                         chmod +x get-setenv.sh
        //                         ./get-setenv.sh configurations/${environment}.tfvars
        //                         terraform apply -auto-approve -var-file configurations/${environment}.tfvars
        //                         '''
        //                         env.service_account_email = sh ( script: 'terraform output service_account_email', returnStdout: true ).trim()
        //                     }
        //                 }
        //             }
        //         }

        //         // build Docker Image
        //         stage("[Stage] Build Docker Image") {
        //             // build docker image and upload to GCR
        //             steps {
        //                 script {
        //                     env.containerName   = getContainerName(isFeatureBranch, service_name, branch, environment)
        //                     env.dbpassword = sh ( script: 'gcloud secrets versions access 1 --secret=mysql8_root_secrets', returnStdout: true).trim()
        //                     env.googleApikey = sh ( script: 'gcloud secrets versions access 1 --secret=google_api_secrets', returnStdout: true).trim()
        //                     env.cseApikey = sh ( script: 'gcloud secrets versions access 1 --secret=cse_api_secrets', returnStdout: true).trim()
        //                     env.youtubeApikey = sh ( script: 'gcloud secrets versions access 1 --secret=youtube_api_secrets', returnStdout: true).trim()
        //                     env.tableauAdminUser = sh ( script: 'gcloud secrets versions access 1 --secret=tableau_admin_user_secrets', returnStdout: true).trim()
        //                     env.tableauAdminPassword = sh ( script: 'gcloud secrets versions access 1 --secret=tableau_admin_pw_secrets', returnStdout: true).trim()
        //                     env.esAdminUser = sh ( script: 'gcloud secrets versions access 1 --secret=es_admin_user_secrets', returnStdout: true).trim()
        //                     env.esAdminPassword = sh ( script: 'gcloud secrets versions access 1 --secret=es_admin_pw_secrets', returnStdout: true).trim()
        //                     env.apimApikey = sh ( script: 'gcloud secrets versions access 1 --secret=apim_api_secrets', returnStdout: true).trim()
        //                     env.lvSecretkey = sh ( script: 'gcloud secrets versions access 1 --secret=lvi_key_secrets', returnStdout: true).trim()
        //                     env.Redis_IP = sh ( script: 'gcloud redis instances list --region ${REGION} --filter=name:projects/${project_id}/locations/${REGION}/instances/redis-an3-mip-${environment}-session-01 --format="value(host)"', returnStdout: true).trim()
        //                     sh ("echo 'container name is :   ' ${containerName}")
        //                     sh '''
        //                     VERSION=2.0.0
        //                     OS=linux  # or "darwin" for OSX, "windows" for Windows.
        //                     ARCH=amd64  # or "386" for 32-bit OSs, "arm64" for ARM 64.
        //                     curl -fsSL "https://github.com/GoogleCloudPlatform/docker-credential-gcr/releases/download/v${VERSION}/docker-credential-gcr_${OS}_${ARCH}-${VERSION}.tar.gz" | tar xz --to-stdout ./docker-credential-gcr > /usr/local/bin/docker-credential-gcr && chmod +x /usr/local/bin/docker-credential-gcr
        //                     '''
        //                     sh '''
        //                     cp -p src/main/resources/application.yml src/main/resources/application.origin
        //                     sed -i -e s#_TAG_#${BUILD_NUMBER}#g build.gradle
        //                     set +x
        //                     sed -i s#MI_SCHEME#${DB_SCHEME}#g src/main/resources/application.yml
        //                     sed -i s#redisHost#${Redis_IP}#g src/main/resources/application.yml
        //                     sed -i s#appuser#beappuser#g src/main/resources/application.yml
        //                     sed -i s#GOOGLE_API_KEY#${googleApikey}#g src/main/resources/application.yml
        //                     sed -i s#CSE_API_KEY#${cseApikey}#g src/main/resources/application.yml
        //                     sed -i s#YOUTUBE_API_KEY#${youtubeApikey}#g src/main/resources/application.yml
        //                     sed -i s#TABLEAU_ADMIN_USER#${tableauAdminUser}#g src/main/resources/application.yml
        //                     sed -i s#TABLEAU_ADMIN_PASSWD#${tableauAdminPassword}#g src/main/resources/application.yml
        //                     sed -i s#ES_ADMIN_USER#${esAdminUser}#g src/main/resources/application.yml
        //                     sed -i s#ES_ADMIN_PASSWD#${esAdminPassword}#g src/main/resources/application.yml
        //                     sed -i s#APIM_API_KEY#${apimApikey}#g src/main/resources/application.yml
        //                     sed -i s#LV_SECRET_KEY#${lvSecretkey}#g src/main/resources/application.yml
        //                     set -x
        //                     '''
        //                     userSql = 'src/main/resources/flyway/common_sql/V0_0_000_0__Initial_DB_User.sql'
        //                     userList = [ 'beappuser', 'batchuser', 'functionuser', 'viewer', 'tableau_viewer' ]
        //                     for (item in userList) {
        //                         userPassword = sh ( script: "gcloud secrets versions access 1 --secret=mysql8_${item}_secrets", returnStdout: true).trim()
        //                         if (item == 'viewer') {
        //                             createDBUserType(userSql, item, userPassword, DB_SCHEME, 'select')
        //                         } else {
        //                             createDBUserType(userSql, item, userPassword, DB_SCHEME, 'CRUD')
        //                         }
        //                         if (item == 'beappuser') {
        //                             sh '''
        //                             set +x
        //                             userPassword=`gcloud secrets versions access 1 --secret=mysql8_beappuser_secrets`
        //                             set -x
        //                             '''
        //                             env.appPassword = sh ( script: "echo -n ${userPassword} | base64 ", returnStdout: true).trim()
        //                         }
        //                     }
        //                     sh './gradlew jib --image=gcr.io/${project_id}/${containerName}'
        //                 }
        //             }
        //         }

        //         stage("[Stage] GKE Deploy"){
        //             steps {
        //                 script {
        //                     env.fwPassword = sh ( script: "echo -n ${dbpassword} | base64 ", returnStdout: true).trim()
        //                     sh '''
        //                     CLUSTER_NAME="gke-an3-mip-${environment}-gke-cluster-01"
        //                     gcloud container clusters get-credentials ${CLUSTER_NAME} --region $REGION --project ${project_id}
        //                     '''
        //                     sh '''
        //                     DBConnectionName=`gcloud sql instances describe gdb-an3-mip-${environment}-mysql8-mi-01 --format="value(connectionName)"`
        //                     DBPort="3306"
        //                     cp -pr cicd/charts cicd/charts_origin
        //                     sed -i -e s#_RELEASE_VERSION_#${BUILD_NUMBER}#g cicd/charts/Chart.yaml
        //                     sed -i "s/_PORT_NO_/${PORT_NO}/g" cicd/charts/templates/deployment.yaml
        //                     sed -i "s/_SERVICE_ACCOUNT_/${service_account_email}/g" cicd/charts/values.yaml
        //                     helm upgrade --install --namespace ${namespace} \
        //                                  --set values.serviceAccount.name=${ksa_name} \
        //                                  --set sqlProxy.INSTANCE_CONNECTION_NAME=${DBConnectionName} --set sqlProxy.DB_PORT=${DBPort} \
        //                                  --set image.repository=gcr.io/${project_id}/${containerName} --set image.tag=${BUILD_NUMBER} \
        //                                  --set service.port=${PORT_NO} --set ingress.hosts[0].host=${DOMAINURL} --set ingress.hosts[0].paths={'/'} \
        //                                  --set envVariables.profiles=${PROFILES} --set pdbMinAvailable=${pdb} \
        //                                  --set envVariables.DBpwd=${fwPassword} --set envVariables.appPassword=${appPassword} \
        //                                  --create-namespace --debug --wait ${namespace} ./cicd/charts
        //                     '''
        //                 }
        //                 timeout(time: 3, unit: 'MINUTES') {
        //                     script {
        //                         desiredReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.spec.replicas}'", returnStdout: true ).trim()
        //                         readyReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.status.readyReplicas}'", returnStdout: true ).trim()
        //                         while(desiredReplicas != readyReplicas) {
        //                             readyReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.status.readyReplicas}'", returnStdout: true ).trim()
        //                             sleep(15)
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        //         stage('[Stage] Flyway') {
        //             steps{
        //                 script {
        //                     sh '''
        //                     for collection in api-tests/flyway/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-stage.postman_environment.json; done
        //                     '''
        //                 }
        //             }
        //         }
        //         stage("[Stage] Health Check"){
        //             steps {
        //                 script {
        //                     sh '''
        //                     for collection in api-tests/health/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-stage.postman_environment.json --insecure; done
        //                     '''
        //                 }
        //             }
        //         }

        //         stage("[Stage] Smoke Test") {
        //             steps {
        //                 script {
        //                     // Report the actual result to slack(#jenkinspipeline_smoke) and proceed to the next step
        //                     def output = sh(returnStatus: true, script: 'for collection in api-tests/smoke/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-stage.postman_environment.json --insecure; done')
        //                     def autual_result = output.toString() == '0' ? 'SUCCESS' : 'FAILURE'
        //                     notifySlack(slack_workspace, slack_token_id, slack_channel_smoke, autual_result)
        //                 }
        //             }
        //         }

        //         stage("Approval Step to deploy Production from Stage") {
        //             steps {
        //                 input message : "Do you want to deploy Production?", submitter : "${submitter}"
        //             }
        //         }

        //         stage("[PROD] Environment") {
        //             steps {
        //                 script {
        //                     env.environment         = 'prd'
        //                     env.project_id          = 'pjt-mip-prd-1'
        //                     env.PORT_NO             = sh(script: "grep -A1 server src/main/resources/application.yml | grep port | grep -v 'server-port' | awk '{print \$2}'", returnStdout: true).trim()

        //                     env.DOMAINURL = "mi.koreabuild.com"
        //                     env.DOMAINURL = "be" + "." + env.DOMAINURL
        //                     env.namespace = env.service_name + "-" + env.environment        // be-prd
        //                     env.ksa_name = env.service_name + "-" + env.environment         // mi-be-prd
        //                     env.PROFILES   = "prod"
        //                     env.DB_SCHEME = "mi"
        //                     env.pdb = '1'
        //                     sh ("echo 'DomainURL is :   ' ${DOMAINURL}")

        //                     // make gcp credentials
        //                     sh 'mkdir -p creds'
        //                     sh 'echo $SVC_ACCOUNT_PRD_KEY | base64 -d > $GOOGLE_APPLICATION_CREDENTIALS'

        //                     // gcloud init
        //                     sh 'gcloud auth activate-service-account --key-file ${GOOGLE_APPLICATION_CREDENTIALS}'
        //                     sh 'gcloud auth configure-docker'
        //                     sh 'gcloud config set project ${project_id}'

        //                     // store-pass
        //                     def storepass = sh ( script: 'gcloud secrets versions access 1 --secret sso_storepass_secrets', returnStdout: true).trim()
        //                     sh ("sed -i s#STOREPASS#${storepass}#g src/main/resources/application*.yml")
        //                     sh ("grep 'store-pass' src/main/resources/application-prod.yml")

        //                     // sso key
        //                     sh 'gcloud secrets versions access 1 --secret sso_private_key_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/mi_prd.jks'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgcns_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgcns.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lge_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lge.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgcare_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgcare.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgchem_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgchem.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgdisplay_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgdisplay.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lgensol_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lgensol.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lginnotek_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lginnotek.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lguplus_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lguplus.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lxhausys_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lxhausys.xml'
        //                     sh 'gcloud secrets versions access 1 --secret sso_lxsemicon_idp_metadata_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/lxsemicon.xml'

        //                     // bdp secret key
        //                     sh 'gcloud secrets versions access 1 --secret bdp_private_key_secrets --format "value(payload.data.decode(base64).encode(base64))" | base64 --decode > src/main/resources/security/pjt-bdp-prd-1.json'

        //                     // kms
        //                     sh 'gcloud secrets versions access 1 --secret mysql8_data_encrypt_key_secrets > src/main/resources/security/db-data-encrypt-prd.json'

        //                     sh 'ls -ls src/main/resources/security'
        //                 }
        //             }
        //         }

        //         stage("[PROD] Apply Terraform"){
        //             steps {
        //                 dir('cicd/terraform'){
        //                     script {
        //                         sh '''
        //                         sed -i -e \"s#_BRANCH_NAME_#${environment}#g\" configurations/${environment}.tfvars
        //                         sed -i -e \"s#_NAMESPACE_#${namespace}#g\" configurations/${environment}.tfvars
        //                         sed -i -e \"s#_KSA_NAME_#${ksa_name}#g\" configurations/${environment}.tfvars
        //                         '''
        //                         sh '''
        //                         chmod +x get-setenv.sh
        //                         ./get-setenv.sh configurations/${environment}.tfvars
        //                         terraform apply -auto-approve -var-file configurations/${environment}.tfvars
        //                         '''
        //                         env.service_account_email = sh ( script: 'terraform output service_account_email', returnStdout: true ).trim()
        //                     }
        //                 }
        //             }
        //         }

        //         stage("[PROD] Build Docker Image") {
        //             // build docker image and upload to GCR
        //             steps {
        //                 script {
        //                     env.containerName   = getContainerName(isFeatureBranch, service_name, branch, environment)
        //                     env.dbpassword = sh ( script: 'gcloud secrets versions access 1 --secret=mysql8_root_secrets', returnStdout: true).trim()
        //                     env.googleApikey = sh ( script: 'gcloud secrets versions access 1 --secret=google_api_secrets', returnStdout: true).trim()
        //                     env.cseApikey = sh ( script: 'gcloud secrets versions access 1 --secret=cse_api_secrets', returnStdout: true).trim()
        //                     env.youtubeApikey = sh ( script: 'gcloud secrets versions access 1 --secret=youtube_api_secrets', returnStdout: true).trim()
        //                     env.tableauAdminUser = sh ( script: 'gcloud secrets versions access 1 --secret=tableau_admin_user_secrets', returnStdout: true).trim()
        //                     env.tableauAdminPassword = sh ( script: 'gcloud secrets versions access 1 --secret=tableau_admin_pw_secrets', returnStdout: true).trim()
        //                     env.esAdminUser = sh ( script: 'gcloud secrets versions access 1 --secret=es_admin_user_secrets', returnStdout: true).trim()
        //                     env.esAdminPassword = sh ( script: 'gcloud secrets versions access 1 --secret=es_admin_pw_secrets', returnStdout: true).trim()
        //                     env.apimApikey = sh ( script: 'gcloud secrets versions access 1 --secret=apim_api_secrets', returnStdout: true).trim()
        //                     env.lvSecretkey = sh ( script: 'gcloud secrets versions access 1 --secret=lvi_key_secrets', returnStdout: true).trim()
        //                     env.Redis_IP = sh ( script: 'gcloud redis instances list --region ${REGION} --filter=name:projects/${project_id}/locations/${REGION}/instances/redis-an3-mip-${environment}-session-01 --format="value(host)"', returnStdout: true).trim()
        //                     sh ("echo 'container name is :   ' ${containerName}")
        //                     sh '''
        //                     VERSION=2.0.0
        //                     OS=linux  # or "darwin" for OSX, "windows" for Windows.
        //                     ARCH=amd64  # or "386" for 32-bit OSs, "arm64" for ARM 64.
        //                     curl -fsSL "https://github.com/GoogleCloudPlatform/docker-credential-gcr/releases/download/v${VERSION}/docker-credential-gcr_${OS}_${ARCH}-${VERSION}.tar.gz" | tar xz --to-stdout ./docker-credential-gcr > /usr/local/bin/docker-credential-gcr && chmod +x /usr/local/bin/docker-credential-gcr
        //                     '''
        //                     sh '''
        //                     cp -p src/main/resources/application.origin src/main/resources/application.yml
        //                     sed -i -e s#_TAG_#${BUILD_NUMBER}#g build.gradle
        //                     set +x
        //                     sed -i s#MI_SCHEME#${DB_SCHEME}#g src/main/resources/application.yml
        //                     sed -i s#redisHost#${Redis_IP}#g src/main/resources/application.yml
        //                     sed -i s#appuser#beappuser#g src/main/resources/application.yml
        //                     sed -i s#GOOGLE_API_KEY#${googleApikey}#g src/main/resources/application.yml
        //                     sed -i s#CSE_API_KEY#${cseApikey}#g src/main/resources/application.yml
        //                     sed -i s#YOUTUBE_API_KEY#${youtubeApikey}#g src/main/resources/application.yml
        //                     sed -i s#TABLEAU_ADMIN_USER#${tableauAdminUser}#g src/main/resources/application.yml
        //                     sed -i s#TABLEAU_ADMIN_PASSWD#${tableauAdminPassword}#g src/main/resources/application.yml
        //                     sed -i s#ES_ADMIN_USER#${esAdminUser}#g src/main/resources/application.yml
        //                     sed -i s#ES_ADMIN_PASSWD#${esAdminPassword}#g src/main/resources/application.yml
        //                     sed -i s#APIM_API_KEY#${apimApikey}#g src/main/resources/application.yml
        //                     sed -i s#LV_SECRET_KEY#${lvSecretkey}#g src/main/resources/application.yml
        //                     set -x
        //                     '''
        //                     sh "rm -f src/main/resources/flyway/common_sql/V0_0_000_0__Initial_DB_User.sql"
        //                     userSql = 'src/main/resources/flyway/common_sql/V0_0_000_0__Initial_DB_User.sql'
        //                     userList = [ 'beappuser', 'batchuser', 'functionuser', 'viewer', 'tableau_viewer' ]
        //                     for (item in userList) {
        //                         userPassword = sh ( script: "gcloud secrets versions access 1 --secret=mysql8_${item}_secrets", returnStdout: true).trim()
        //                         if (item == 'viewer') {
        //                             createDBUserType(userSql, item, userPassword, DB_SCHEME, 'select')
        //                         } else {
        //                             createDBUserType(userSql, item, userPassword, DB_SCHEME, 'CRUD')
        //                         }
        //                         if (item == 'beappuser') {
        //                             sh '''
        //                             set +x
        //                             userPassword=`gcloud secrets versions access 1 --secret=mysql8_beappuser_secrets`
        //                             set -x
        //                             '''
        //                             env.appPassword = sh ( script: "echo -n ${userPassword} | base64 ", returnStdout: true).trim()
        //                         }
        //                     }
        //                     sh './gradlew jib --image=gcr.io/${project_id}/${containerName}'
        //                 }
        //             }
        //         }

        //         stage("[PROD] GKE Deploy"){
        //             steps {
        //                 script {
        //                     env.fwPassword = sh ( script: "echo -n ${dbpassword} | base64 ", returnStdout: true).trim()
        //                     sh '''
        //                     CLUSTER_NAME="gke-an3-mip-${environment}-gke-cluster-01"
        //                     gcloud container clusters get-credentials ${CLUSTER_NAME} --region $REGION --project ${project_id}
        //                     '''
        //                     sh '''
        //                     DBConnectionName=`gcloud sql instances describe gdb-an3-mip-${environment}-mysql8-mi-01 --format="value(connectionName)"`
        //                     DBPort="3306"
        //                     rm -r cicd/charts
        //                     mv cicd/charts_origin cicd/charts
        //                     sed -i -e s#_RELEASE_VERSION_#${BUILD_NUMBER}#g cicd/charts/Chart.yaml
        //                     sed -i "s/_PORT_NO_/${PORT_NO}/g" cicd/charts/templates/deployment.yaml
        //                     sed -i "s/_SERVICE_ACCOUNT_/${service_account_email}/g" cicd/charts/values.yaml
        //                     helm upgrade --install --namespace ${namespace} \
        //                                  --set values.serviceAccount.name=${ksa_name} \
        //                                  --set sqlProxy.INSTANCE_CONNECTION_NAME=${DBConnectionName} --set sqlProxy.DB_PORT=${DBPort} \
        //                                  --set image.repository=gcr.io/${project_id}/${containerName} --set image.tag=${BUILD_NUMBER} \
        //                                  --set service.port=${PORT_NO} --set ingress.hosts[0].host=${DOMAINURL} --set ingress.hosts[0].paths={'/'} \
        //                                  --set envVariables.profiles=${PROFILES} --set pdbMinAvailable=${pdb} \
        //                                  --set envVariables.DBpwd=${fwPassword} --set envVariables.appPassword=${appPassword} \
        //                                  --create-namespace --debug --wait ${namespace} ./cicd/charts
        //                     '''
        //                 }
        //                 timeout(time: 3, unit: 'MINUTES') {
        //                     script {
        //                         desiredReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.spec.replicas}'", returnStdout: true ).trim()
        //                         readyReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.status.readyReplicas}'", returnStdout: true ).trim()
        //                         while(desiredReplicas != readyReplicas) {
        //                             readyReplicas = sh ( script: "kubectl get rs -n \${namespace} `kubectl get rs -n \${namespace} --sort-by=.metadata.creationTimestamp | tail -1 | awk '{print \$1}'` -o=jsonpath='{.status.readyReplicas}'", returnStdout: true ).trim()
        //                             sleep(15)
        //                         }
        //                     }
        //                 }
        //             }
        //         }

        //         stage('[Prod] Flyway') {
        //             steps{
        //                 script {
        //                     sh '''
        //                     for collection in api-tests/flyway/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-prod.postman_environment.json; done
        //                     '''
        //                 }
        //             }
        //         }
        //         stage("[Prod] Health Check"){
        //             steps {
        //                 script {
        //                     sh '''
        //                     for collection in api-tests/health/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-prod.postman_environment.json --insecure; done
        //                     '''
        //                 }
        //             }
        //         }

        //         stage("[Prod] Smoke Test") {
        //             steps {
        //                 script {
        //                     sh '''
        //                     for collection in api-tests/smoke/*.json; do newman run \"$collection\" --environment api-tests/postman_environment/MIBE-Environment-prod.postman_environment.json --insecure; done
        //                     '''
        //                 }
        //             }
        //         }
        //     }
        // }
     }
 }