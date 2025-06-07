FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY . .
RUN mvn clean package -DskipTests

FROM ubuntu:22.04

RUN apt-get update && apt-get install -y openjdk-17-jdk \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN useradd -ms /bin/bash appuser

WORKDIR /app

COPY --from=builder /build/target/salvando-0.0.1-SNAPSHOT.jar app.jar

RUN chown -R appuser:appuser /app

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]