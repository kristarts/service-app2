FROM openjdk:17
COPY target/service-app2-0.0.1-SNAPSHOT.jar service-app.jar
ENTRYPOINT ["java","-jar","service-app.jar"]