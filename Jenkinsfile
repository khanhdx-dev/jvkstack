pipeline {

    agent any

    tools {
        maven 'jvk-stack-maven'
    }
    environment {
        DOCKERHUB_LOGIN = credentials('dockerhub-acc')
    }
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Scan') {
             steps {
                withSonarQubeEnv(installationName: 'sonarqube-jenkins') {
                  sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                }
             }
        }

        stage('Packaging/Pushing image to Dockerhub') {
            steps {
                sh 'ls -la'
                sh 'docker login -u $DOCKERHUB_LOGIN_USR -p $DOCKERHUB_LOGIN_PSW https://index.docker.io/v1/'
                sh 'docker build -t khanhdx/jvkstack .'
                sh 'docker push khanhdx/jvkstack'
            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull khanhdx/jvkstack'
                sh 'docker container stop khanhdx-jvkstack || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name khanhdx-jvkstack -p 8081:8080 --network dev khanhdx/jvkstack'
            }
        }

    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}
