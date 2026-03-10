FROM eclipse-temurin:21-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/games-0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
