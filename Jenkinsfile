pipeline{
  agent any
  environment {
        DOCKERHUB_CREDS = credentials('dockerhub-token')
    }
  
 
    stages{
        stage('Build Image'){
            steps{
                sh 'docker build -t android330/e-commerce-backend:latest .'   
            }
        }
        
        stage('Deploy Image'){
            steps{
                sh 'docker login --username=${DOCKERHUB_CREDS_USR} --password=${DOCKERHUB_CREDS_PSW}'
                sh 'docker push android330/e-commerce-backend:latest'
            }
        }
    }
}
