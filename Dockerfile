FROM eclipse-temurin:8-jre

WORKDIR /app

COPY target/Snowman.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
