FROM amazoncorretto:11-alpine-jdk
MAINTAINER DGD
COPY target/dgdV4-0.0.1-SNAPSHOT.jar  dgd-app.jar
ENTRYPOINT ["java","-jar","/dgd-app.jar"]
