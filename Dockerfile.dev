FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

MAINTAINER Julian Duru < durutheguru@gmail.com >

WORKDIR /build/


COPY pom.xml .

# Cache Local Maven dependencies
RUN mvn dependency:go-offline


COPY src /build/src/

RUN mvn package


# 2nd Stage of Build -

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/omarze-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "omarze-0.0.1-SNAPSHOT.jar"]

