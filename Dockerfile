
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/Jym-1.0-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
