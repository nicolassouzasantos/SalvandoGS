FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

RUN adduser -D -H -s /bin/sh appuser

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

RUN chown appuser:appuser app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
