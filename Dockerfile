FROM maven:3.8.4-jdk-11-slim
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/spring-boot-test-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]