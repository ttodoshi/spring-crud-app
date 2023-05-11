FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn -B package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/authorization-service-2.7.5.jar ./authorization-service.jar
CMD ["java", "-jar", "authorization-service.jar"]