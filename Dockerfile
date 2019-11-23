FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Julian Duru < durutheguru@gmail.com >

WORKDIR /build/

COPY pom.xml .

COPY src /build/src/

RUN mvn package



# 2nd Stage of Build -

FROM openjdk:8-jre-alpine

WORKDIR /app


ENV RELEASE_TAG


COPY --from=MAVEN_BUILD /build/target/omarze-${RELEASE_TAG}-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "omarze-${RELEASE_TAG}-SNAPSHOT.jar"]

