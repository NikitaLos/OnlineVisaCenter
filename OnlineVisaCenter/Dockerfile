FROM openjdk:8-j} app.jardk-alpine
VOLUME /tmp
ARG JAR_FILE=target/online-visa-center-1.0-SNAPSHOT.jar
ADD ${JAR_FILE
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]