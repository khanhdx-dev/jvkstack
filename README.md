
# JvkStack App

Springboot App using Jenkins cicd pipeline integrate with SonarQube


## Authors

- [@khanhdx](https://github.com/khanhdx-dev)


## Prerequesites

- [Docker](https://www.docker.com/)
- [Java 17](https://www.java.com/en/)


## Installation

Install docker-dind:

```bash
  docker run \
  --name jenkins-docker \
  --rm \
  --detach \
  --privileged \
  --network jenkins \
  --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:8081 \
  docker:dind \
  --storage-driver overlay2
```

Install Jenkins-docker:

```bash
docker run \
  --name jenkins-blueocean \
  --restart=on-failure \
  --detach \
  --network jenkins \
  --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client \
  --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 \
  --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:2.426.2-1
```

Install Sonar-docker:
```bash
docker volume create --name sonarqube_data
docker volume create --name sonarqube_logs
docker volume create --name sonarqube_extensions
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
```
## Running step

- After starting Jenkins via Docker command, go to: [localhost:8080](http://localhost:8080)

- Login and install required tools: Maven, Docker,...

- Create credential to access Dockerhub in order to push docker image later.

- Config pipeline to get source code from Git repository.

- Add SonarQube server URL to pipeline.

- Start the pipeline

- Enjoy ^_^
