pipeline {
          agent any
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
                stage('NEXUS'){
            steps{
                  echo "nexus"
                  sh ' mvn deploy -DskipTests'
            }
        }
                            /* DOCKER */



               stage('Build Docker Image') {
                  steps {
                  sh 'docker build -t marwaboudellaaesprit/docker_spring:2.2.4 .'
                  }
               }

               stage('Push Docker Image') {
                  steps {
                  withCredentials([string(credentialsId: 'Docker-pwd', variable: 'DockerhubPWS')]) {
                  sh "docker login -u marwaboudellaaesprit -p ${DockerhubPWS}"
                  }
                  sh 'docker push marwaboudellaaesprit/docker_spring:2.2.4'
                  }
               }
               stage('DOCKER COMPOSE') {
                  steps {
                  sh 'docker-compose up -d --build'
                  }
               }
               }

          }
