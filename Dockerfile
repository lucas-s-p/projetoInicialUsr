FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /usr/src/usrfacil

COPY usrfacil/pom.xml .

RUN mvn clean install

COPY usrfacil/src ./src

RUN mvn clean install

FROM openjdk:17-jdk-slim

WORKDIR /usr/src/usrfacil

COPY --from=build /usr/src/usrfacil/target/usrfacil-0.0.1-SNAPSHOT.jar /home/lucas/