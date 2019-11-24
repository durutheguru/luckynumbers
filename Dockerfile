FROM openjdk:8-jre-alpine

WORKDIR /app


ARG release_version


COPY /target/omarze-$release_version-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "omarze-$release_version-SNAPSHOT.jar"]

