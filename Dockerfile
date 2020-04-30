FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/mini-social-network-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} mini-social-network-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/mini-social-network-0.0.1-SNAPSHOT.jar"]