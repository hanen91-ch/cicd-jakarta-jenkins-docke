pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        IMAGE = '6401185/a18_hanenchtourou_final_1318'
    }

    stages {
        stage('Build Maven') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Deploiement Tomcat') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat-creds',
                                          path: '',
                                          url: 'http://host.docker.internal:8086')],
                       contextPath: 'tp-finale',
                       war: 'target/*.war'
            }
        }

        stage('Build image Docker') {
            steps {
                sh 'docker build -t $IMAGE:$BUILD_NUMBER -t $IMAGE:latest .'
            }
        }

        stage('Push Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                  usernameVariable: 'DH_USER',
                                                  passwordVariable: 'DH_PASS')]) {
                    sh 'echo $DH_PASS | docker login -u $DH_USER --password-stdin'
                    sh 'docker push $IMAGE:$BUILD_NUMBER'
                    sh 'docker push $IMAGE:latest'
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
        }
    }
}