FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn -B package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/lesson-service-2.7.5.jar ./lesson-service.jar
CMD ["java", "-jar", "lesson-service.jar"]