# ==========================================================
# STAGE 1: BUILD (Maven + JDK 21)
# ==========================================================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
ENV LANG=C.UTF-8
RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8

# ==========================================================
# STAGE 2: RUNTIME
# ==========================================================
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/esgames-endpoints-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]