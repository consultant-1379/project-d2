FROM openjdk:8-jdk-alpine

VOLUME /tmp
COPY target/*.jar CI-stability-project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/CI-stability-project-0.0.1-SNAPSHOT.jar"]