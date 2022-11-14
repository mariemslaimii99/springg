pipeline {
          agent any
          stages{
            stage('Checkout GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'Olfa_Operateur',
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
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                 }
              }
             stage("publish to nexus") {
            steps {
                script {
                configFileProvider([configFile(fileId: 'olfa', variable: 'settingnexus')]) {
                    sh 'mvn  -B -DskipTests deploy -s $setting'

}                }
            }
        }

                            /* DOCKER */



               stage('Build Docker Image') {
                  steps {
                  sh 'docker build -t olfababai/docker_spring:2.2.4 .'
                  }
               }

               stage('Push Docker Image') {
                  steps {
                  withCredentials([string(credentialsId: 'Docker-pwd', variable: 'Olfa07490115')]) {
                  sh "docker login -u olfababai -p ${Olfa07490115}"
                  }
                  sh 'docker push olfababai/docker_spring:2.2.4'
                  }
               }
               stage('DOCKER COMPOSE') {
                  steps {
                  sh 'docker-compose up -d --build'
                  }
               }
               }

          }
