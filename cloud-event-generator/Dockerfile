FROM cescoffier/native-base:latest
COPY target/*-runner /application
EXPOSE 8080
ENTRYPOINT ["./application", "-Dquarkus.http.host=0.0.0.0"]
