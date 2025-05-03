FROM openjdk:11

WORKDIR /app

COPY target/urlShorter-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]