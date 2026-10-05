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
    }
}