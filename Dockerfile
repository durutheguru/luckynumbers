FROM openjdk:8-jre-alpine

WORKDIR /app


ARG release_version=${RELEASE_TAG}


COPY /target/omarze-${RELEASE_TAG}-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "omarze-${RELEASE_TAG}-SNAPSHOT.jar"]

