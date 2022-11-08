pipeline {
          agent any
          stages{
            stage('Checkout GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'Marwa',
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
                sh 'mvn package'
                }
             }
              stage('MVN SONARQUBE '){
                 steps{
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=marwa'
                 }
              }
               stage("nexus deploy"){
                               steps{
                                nexusArtifactUploader artifacts: [[artifactId: 'achat', classifier: '', file: '/var/lib/jenkins/workspace/DevOpsBack/target/achat-1.0.jar', type: 'jar']], credentialsId: 'nexus-snapshots', groupId: 'tn.esprit.rh', nexusUrl: '192.168.56.11:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'nexus-snapshots', version: '1.0.0'
                               }
                            }

                            /* DOCKER */



                                           stage('Build Docker Image') {
                                                           steps {
                                                           sh 'docker build -t aymenjbara/dockerfile_spring:2.2.4 .'
                                                           }
                                                        }

                                                        stage('Push Docker Image') {
                                                             steps {
                                                               withCredentials([string(credentialsId: 'DockerhubPWS', variable: 'DockerhubPWS')]) {
                                                               sh "docker login -u aymenjbara -p ${DockerhubPWS}"
                                                               }
                                                               sh 'docker push aymenjbara/dockerfile_spring:2.2.4'
                                                             }
                                                        }
                                                        stage('DOCKER COMPOSE') {
                                                             steps {
                                                                sh 'docker-compose up -d --build'
                                                             }
                                                        }
                                                    }
                                                       
                                                    }