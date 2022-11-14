pipeline {
          agent any
          stages{
            stage('Recuperation GIT'){
                steps{
                    echo 'Pulling...';
                    git branch: 'Olfa_Operateur',
                    url : 'https://github.com/Olfa-Babai/achat.git';
                }

            }
            stage('Mvn clean'){
            steps{
                echo 'Pulling...';
                sh 'mvn clean'
                }
            }
             stage('Compilation'){
                steps{
                sh 'mvn compile'
                }
             }
             stage('Mvn package'){
                steps{
                sh 'mvn package -DskipTests=true'
                }
             }
             stage('Test unitaire'){
                steps{
                sh 'mvn test'
                }
             }
              stage('Sonar test qualite '){
                 steps{
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                 }
              }
             stage("Version nexus") {
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
                  withCredentials([string(credentialsId: 'dpwd', variable: 'Olfa07490115')]) {
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
