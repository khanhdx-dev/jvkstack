pipeline {

    agent any

    tools {
        maven 'jvk-stack-maven'
    }
//     environment {
//         MYSQL_ROOT_LOGIN = credentials('mysql-root-login')
//     }
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Packaging/Pushing image') {
            steps {
                echo 'Pushing to dockerhub'
                withDockerRegistry(credentialsId: 'dockerhub-acc', url: '') {
                    sh 'docker build -t khanhdx/jvkstack .'
                    sh 'docker push khanhdx/jvkstack'
                }
            }
        }

//         stage('Deploy MySQL to DEV') {
//             steps {
//                 echo 'Deploying and cleaning'
//                 sh 'docker image pull mysql:8.0'
//                 sh 'docker network create dev || echo "this network exists"'
//                 sh 'docker container stop khalid-mysql || echo "this container does not exist" '
//                 sh 'echo y | docker container prune '
//                 sh 'docker volume rm khalid-mysql-data || echo "no volume"'
//
//                 sh "docker run --name khalid-mysql --rm --network dev -v khalid-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
//                 sh 'sleep 20'
//                 sh "docker exec -i khalid-mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script"
//             }
//         }

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
