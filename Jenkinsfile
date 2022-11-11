pipeline {
          agent any
          
          environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10/:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"


    }
          stages{
            stage('Checkout GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'brancheEya',
                    url : 'https://github.com/Olfa-Babai/achat.git';
                }

            }
            stage('MVN CLEAN'){
            steps{
                echo 'Pulling...';
                sh 'mvn clean'
                }
            }
             stage('MVN COMPILE'){
                steps{
                sh 'mvn compile'
                }
             }
             stage('MVN PACKAGE'){
                steps{
                sh 'mvn package -DskipTests=true'
                }
             }
             stage('MVN Test'){
                steps{
                sh 'mvn test'
                }
             }
              stage('MVN SONARQUBE '){
                 steps{
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=54243838'
                 }
              }
              /* DOCKER */
 stage('Build Docker Image') {
                  steps {
                  sh 'docker build -t ouellani/docker_spring:2.2.4 .'
                  }
               }

               stage('Push Docker Image') {
                  steps {
                  withCredentials([string(credentialsId: 'ouellani', variable: '542438388')]) {
                  sh "docker login -u ouellani -p ${542438388}"
                  }
                  sh 'docker push ouellani/docker_spring:2.2.4'
                  }
               }
               stage('DOCKER COMPOSE') {
                  steps {
                  sh 'docker-compose up -d --build'
                  }
               }
               }

          }
