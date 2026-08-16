FROM eclipse-temurin:8-jre

WORKDIR /app

COPY target/enterprise-application-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
