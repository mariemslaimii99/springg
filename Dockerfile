FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD ./target/tpAchatProject-1.0.jar test-docker.jar
ENTRYPOINT ["java","-jar","/test-docker.jar"]
