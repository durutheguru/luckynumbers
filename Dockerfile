FROM openjdk:14-jdk-alpine

WORKDIR /app

ARG release_version=${RELEASE_TAG}

ENV RELEASE_TAG $release_version


COPY /target/omarze-${RELEASE_TAG}-SNAPSHOT.jar /app/omarze.jar

ENTRYPOINT ["java", "-jar", "omarze.jar"]

