pipeline {

    agent any

    tools {
        maven 'jvk-stack-maven'
    }

    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Packaging/Pushing image to Dockerhub') {
            steps {
                sh 'ls -la'
                withDockerRegistry(credentialsId: 'dockerhub-acc', url: '') {
                    sh 'docker build -t khanhdx/jvkstack .'
                    sh 'docker push khanhdx/jvkstack'
                }
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
