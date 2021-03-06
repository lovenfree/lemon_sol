#!groovy
library ('PipelineUtils@develop') // This requires the snippet to be configured in the Jenkins server settings
def isFeatureBranch = !(BRANCH_NAME == 'develop' || BRANCH_NAME == 'stage' || BRANCH_NAME == 'master')
def isDevelopBranch = BRANCH_NAME == 'develop'
def build_id      = env.build_id

env.environment    // This will always start as dev and then change as the pipeline progresses
env.BRANCH_NAME = BRANCH_NAME
env.submitter = 'hun_k, hsj5009'

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
                }
            }
        }

        stage("Common Setup") {
            steps {
                script {
                    ///////////////////////// install java //////////////////////////
                    sh 'apk add openjdk17 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community --allow-untrusted'
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

        stage('Feature Branch') {
            when { expression { isFeatureBranch } }
            stages {
                stage("[Feature] Spring Maven Build") { steps { sh 'echo build' } }
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
                            env.domain              = "admin-dev-lemonaid.singlex.com"
                           //  env.PORT_NO             = sh(script: "grep -A1 server src/main/resources/application.yml | grep port | grep -v 'server-port' | awk '{print \$2}'", returnStdout: true).trim()



                            // make gcp credentials

                            sh 'mkdir -p creds'
                            sh 'echo $SVC_ACCOUNT_DEV_KEY | base64 -d > $GOOGLE_APPLICATION_CREDENTIALS'
                            sh 'echo $GOOGLE_APPLICATION_CREDENTIALS'

                            // gcloud init
                            sh 'gcloud auth activate-service-account --key-file ${GOOGLE_APPLICATION_CREDENTIALS}'
                            sh 'gcloud auth configure-docker'
                            sh 'gcloud config set project ${project_id}'

                            // configmap pull
                            sh 'gcloud secrets versions access 1 --secret=configmap-solution-backend > ./k8/stage-dev/configmap.yaml'


                        }
                    }
                }

                // stage("[DEV-PARALLEL_SONAR&BUILD]"){
                //     parallel {
                //         stage("[DEV] SonarQube analysis") {
                //             steps {
                //                 script {
                //                     try {
                //                         withSonarQubeEnv('did-sonarqube') {
                //                             sh './gradlew sonarqube'
                //                         }
                //                     } catch (Exception e) {
                //                         error("Gradle Build Failed")
                //                     }
                //                 }
                //             }
                //         }

                        stage("[DEV] Build Docker Image") {
                            // build docker image and upload to GCR
                            steps {
                                script {
                                    env.DOCKERIMG = "asia.gcr.io/" + env.project_id + "/" + env.BRANCH_NAME + "/" + env.service_name + ":" + env.BUILD_NUMBER

                                sh './gradlew jib -PjibToImage='+env.DOCKERIMG
                                }
                            }
                        }
                //     }
                // }
                stage("[DEV] Deploy Backend Pod") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                            sh ("sed -i -e s%_SERVICEIMG_%$DOCKERIMG%g ./k8/stage-dev/deployment.yaml")
                            sh ("sed -i -e s%_DOMAIN_%$domain%g ./k8/stage-dev/virtualservice.yaml")
                            sh '''
                            PROJECTID=pjt-did-dev
                            REGION=asia-northeast3
                            CLUSTER_NAME=gke-an3-did-dev
                            gcloud container clusters get-credentials $CLUSTER_NAME --region $REGION --project $PROJECTID
                            gcloud components install kubectl
                            kubectl apply -f ./k8/redis/master -n common-system
                            kubectl apply -f ./k8/redis/slave -n common-system
                            kubectl apply -f ./k8/stage-dev/
                            '''
                        }
                    }
                }
            }
        }

        stage('Stage Branch') {
            when { expression { BRANCH_NAME == 'stage'}}
            stages {
                stage("[STAGE] Environment") {
                    steps {
                        script {
                            env.environment         = 'stg'
                            env.project_id          = 'pjt-did-stg'
                            env.domain              = "admin-stg-lemonaid.singlex.com"

                            // make gcp credentials

                            sh 'mkdir -p creds'
                            sh 'echo $SVC_ACCOUNT_STG_KEY | base64 -d > $GOOGLE_APPLICATION_CREDENTIALS'
                            sh 'echo $GOOGLE_APPLICATION_CREDENTIALS'

                            // gcloud init
                            sh 'gcloud auth activate-service-account --key-file ${GOOGLE_APPLICATION_CREDENTIALS}'
                            sh 'gcloud auth configure-docker'
                            sh 'gcloud config set project ${project_id}'

                            // configmap pull
                            sh 'gcloud secrets versions access 1 --secret=configmap-solution-backend > ./k8/stage-stg/configmap.yaml'

                        }
                    }
                }

                stage("[STAGE] Build Docker Image") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                            env.DOCKERIMG = "asia.gcr.io/" + env.project_id + "/" + env.BRANCH_NAME + "/" + env.service_name + ":" + env.BUILD_NUMBER

                           sh './gradlew jib --image='+env.DOCKERIMG

                        }
                    }
                }
                stage("[STAGE] Deploy Backend Pod") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                            sh ("sed -i -e s%_SERVICEIMG_%$DOCKERIMG%g ./k8/stage-stg/deployment.yaml")
                            sh ("sed -i -e s%_DOMAIN_%$domain%g ./k8/stage-stg/virtualservice.yaml")
                            sh '''
                            PROJECTID=pjt-did-stg
                            REGION=asia-northeast3
                            CLUSTER_NAME=gke-an3-did-stg
                            gcloud container clusters get-credentials $CLUSTER_NAME --region $REGION --project $PROJECTID
                            gcloud components install kubectl
                            kubectl apply -f ./k8/redis/master -n common-system
                            kubectl apply -f ./k8/redis/slave -n common-system
                            kubectl apply -f ./k8/stage-stg/
                            '''
                        }
                    }
                }
            }
        }





        stage('Master Branch') {
            when { expression { BRANCH_NAME == 'master'}}
            stages {
                stage("[PROD] Environment") {
                    steps {
                        script {
                            env.environment         = 'prd'
                            env.project_id          = 'pjt-did-prd'
                            env.domain              = "admin-lemonaid.singlex.com"
                           //  env.PORT_NO             = sh(script: "grep -A1 server src/main/resources/application.yml | grep port | grep -v 'server-port' | awk '{print \$2}'", returnStdout: true).trim()



                            // make gcp credentials

                            sh 'mkdir -p creds'
                            sh 'echo $SVC_ACCOUNT_PRD_KEY | base64 -d > $GOOGLE_APPLICATION_CREDENTIALS'
                            sh 'echo $GOOGLE_APPLICATION_CREDENTIALS'

                            // gcloud init
                            sh 'gcloud auth activate-service-account --key-file ${GOOGLE_APPLICATION_CREDENTIALS}'
                            sh 'gcloud auth configure-docker'
                            sh 'gcloud config set project ${project_id}'

                            // configmap pull
                            sh 'gcloud secrets versions access 1 --secret=configmap-solution-backend > ./k8/stage-prd/configmap.yaml'

                        }
                    }
                }
                stage("[PROD] SonarQube analysis") {
                    steps {
                        script {
                            try {
                                withSonarQubeEnv('did-sonarqube') {
                                    sh './gradlew sonarqube'
                                }
                            } catch (Exception e) {
                                error("Gradle Build Failed")
                            }
                        }
                    }
                }
                stage("[PROD]SonarQube Quality Gate"){
                    steps {
                        script {
                            timeout(time: 5, unit: 'MINUTES') {
                                def qg = waitForQualityGate()
                                if (qg.status != 'OK') {
                                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
                                }
                            }
                        }
                    }
                }
                stage("[PROD] Build Docker Image") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                            env.DOCKERIMG = "asia.gcr.io/" + env.project_id + "/" + env.BRANCH_NAME + "/" + env.service_name + ":" + env.BUILD_NUMBER
                           sh './gradlew jib --image='+env.DOCKERIMG
                        }
                    }
                }
                stage("[PROD] Deploy Backend Pod") {
                    // build docker image and upload to GCR
                    steps {
                        script {
                            sh ("sed -i -e s%_SERVICEIMG_%$DOCKERIMG%g ./k8/stage-prd/deployment.yaml")
                            sh ("sed -i -e s%_DOMAIN_%$domain%g ./k8/stage-prd/virtualservice.yaml")
                            sh '''
                            PROJECTID=pjt-did-prd
                            REGION=asia-northeast3
                            CLUSTER_NAME=gke-an3-did-prd
                            gcloud container clusters get-credentials $CLUSTER_NAME --region $REGION --project $PROJECTID
                            gcloud components install kubectl
                            kubectl apply -f ./k8/redis/master -n common-system
                            kubectl apply -f ./k8/redis/slave -n common-system
                            kubectl apply -f ./k8/stage-prd/
                            '''
                        }
                    }
                }
            }
        }
     }
 }