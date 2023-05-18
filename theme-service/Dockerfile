FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/ /app
ENTRYPOINT ["java", "-jar", "/app/theme-service-0.0.1-SNAPSHOT.jar"]
