pipeline {
    agent any

    environment {
        WAR_NAME = 'eoghanspetitions.war'
        TOMCAT_PATH = '/opt/tomcat11/webapps'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/EoghanPetition/eoghanpetitions'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Package') {
            steps {
                sh './mvnw package'
            }
        }

        stage('Approve Deployment') {
            steps {
                input 'Deploy to EC2?'
            }
        }

        stage('Deploy') {
            steps {
                sh """
                sudo cp target/${WAR_NAME} ${TOMCAT_PATH}/ROOT.war
                sudo /opt/tomcat11/bin/shutdown.sh || true
                sleep 5
                sudo rm -rf ${TOMCAT_PATH}/ROOT
                sudo /opt/tomcat11/bin/startup.sh
                """
            }
        }
    }
}