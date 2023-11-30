FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17
EXPOSE 8080
COPY --from=build /app/target/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]