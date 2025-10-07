# Use Java 17 runtime
FROM eclipse-temurin:17-jre

LABEL authors="alzaidy"

WORKDIR /home/app

# Copy the renamed jar into the container
COPY target/jenkins-tasks-api-mvn.jar tasks-api-mvn.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","tasks-api-mvn.jar"]
