FROM openjdk:8-jdk-alpine
ENV LANG C.UTF-8
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ADD target/application.properties application.properties

EXPOSE 9898

ENTRYPOINT ["java","-Xms1024m","-Xmx2048m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
