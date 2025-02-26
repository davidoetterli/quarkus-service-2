FROM quay.io/quarkus/quarkus-micro-image:latest
WORKDIR /app
COPY target/quarkus-app /app/
CMD ["java", "-jar", "/app/quarkus-run.jar"]
