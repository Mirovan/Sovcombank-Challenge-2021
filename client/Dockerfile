FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11

ENV ws.endpoint=http://localhost:9080/ws
ENV rs.endpoint=http://localhost:9080
ENV server.port=9081

RUN mkdir /app
WORKDIR /app
COPY --from=build /home/app/target/client-1.0-SNAPSHOT.jar /app/starter-kit.jar
EXPOSE 9081
ENTRYPOINT ["java", "-jar", "/app/starter-kit.jar"]

#FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
#
## copy the packaged jar file into our docker image
#COPY target/client-1.0-SNAPSHOT.jar /sovkombank.jar
#
## set the startup command to execute the jar
#CMD ["java", "-jar", "/sovkombank.jar"]