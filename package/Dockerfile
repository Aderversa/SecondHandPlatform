FROM openjdk:17

COPY *.jar /app.jar
COPY application-local.yaml /application.yaml

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
