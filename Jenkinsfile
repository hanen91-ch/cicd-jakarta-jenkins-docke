pipeline {
    agent any

    tools {
        maven 'maven'
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
    }
}