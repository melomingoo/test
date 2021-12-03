FROM adoptopenjdk/openjdk11

ARG JAR_FILE_PATH=target/*.jar
COPY ${JAR_FILE_PATH} app.jar
EXPOSE 1234
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["./mvnw", "clean", "package"]