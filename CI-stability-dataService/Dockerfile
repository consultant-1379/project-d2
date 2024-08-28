FROM openjdk:8-jdk-alpine

VOLUME /tmp
COPY target/*.jar CI-stability-dataService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/CI-stability-dataService-0.0.1-SNAPSHOT.jar"]