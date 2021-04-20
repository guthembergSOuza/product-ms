FROM maven:3.6.1-jdk-8 AS build

WORKDIR /app

COPY pom.xml /app/pom.xml
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn package -DskipTests=true

FROM openjdk:8-jre-alpine

ARG VERSION
ARG RELEASE_DATE

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV APP_PORT=8080
ENV APP_VERSION=${VERSION}
ENV RELEASE_DATE=${RELEASE_DATE}

EXPOSE 8080

RUN apk add --no-cache fontconfig ttf-dejavu

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=ci", "-Djava.awt.headless=true", "app.jar"]
