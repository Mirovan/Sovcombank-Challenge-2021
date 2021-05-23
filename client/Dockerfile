FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

# copy the packaged jar file into our docker image
COPY target/client-1.0-SNAPSHOT.jar /sovkombank-client.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/sovkombank-client.jar"]