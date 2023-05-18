FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/ /app
ENTRYPOINT ["java", "-jar", "/app/lesson-service-0.0.1-SNAPSHOT.jar"]
