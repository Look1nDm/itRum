FROM maven:3.8.5-openjdk-17 as build
WORKDIR /
COPY ./src ./src
COPY pom.xml ./
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /
EXPOSE 8080
COPY --from=build /target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
