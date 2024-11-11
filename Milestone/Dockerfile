#Create a base image that contains Java
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ./target/Milestone-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD [ "java","-jar","Milestone-0.0.1-SNAPSHOT.jar" ]
#Information