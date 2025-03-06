# Quarkus Microservices

Dieses Repository enthält zwei Quarkus-Services, die über Kafka kommunizieren und als Container in einer Docker-Umgebung laufen. Die Services sind:

- **quarkus-service-1**: Blog-Management-Service mit Validierungsmechanismus über Kafka.
- **quarkus-service-2**: Validierungsservice, der Blog-Einträge auf problematische Inhalte überprüft.

## Architektur

- **quarkus-service-1** empfängt Blog-Einträge über eine REST-API und sendet Validierungsanfragen an Kafka.
- **quarkus-service-2** verarbeitet diese Anfragen, validiert den Inhalt und sendet eine Validierungsantwort zurück.
- Die Kommunikation zwischen den Services erfolgt über **Redpanda (Kafka)**.
- Die Datenbank für Blog-Posts ist **MySQL**.

## Voraussetzungen

Stelle sicher, dass folgende Abhängigkeiten auf deinem System installiert sind:

- **Docker** und **Docker Compose**

## Setup für Entwickler

### Repositories klonen
quarkus-service-1:
```sh
git clone git@github.com:davidoetterli/quarkus-service-1.git
```

quarkus-service-2:
```sh
git clone git@github.com:davidoetterli/quarkus-service-2.git
```

### Container starten

1. **Netzwerk für die Kommunikation zwischen Containern erstellen**

   ```sh
   docker network create blog-nw
   ```

2. **Kafka (Redpanda) starten**

   ```sh
   docker run -d --name=redpanda-1 -p 9092:9092 --network blog-nw \
      docker.redpanda.com/redpandadata/redpanda:v23.3.5 redpanda start --advertise-kafka-addr redpanda-1:9092
   ```

3. **MySQL-Datenbank starten**

   ```sh
   docker run --name mysql-db --network blog-nw -e MYSQL_ROOT_PASSWORD=test05 -e MYSQL_DATABASE=blog-db \
      -p 3306:3306 -d mysql:8
   ```

4. **quarkus-service-1 starten**

   ```sh
   docker run -d --name quarkus-service-1-container --network blog-nw -p 8080:8080 ghcr.io/davidoetterli/quarkus-service-1:latest
   ```

5. **quarkus-service-2 starten**

   ```sh
   docker run -d --name quarkus-service-2-container --network blog-nw -p 8081:8081 ghcr.io/davidoetterli/quarkus-service-2:latest
   ```

## Nutzung

1. **Einen neuen Blog-Eintrag erstellen**

   ```sh
   curl -X POST "http://localhost:8080/blog" -H "Content-Type: application/json" -d '{"title":"Test Blog", "content":"Dies ist ein Test"}'
   ```

2. **Alle Blog-Einträge abrufen**

   ```sh
   curl -X GET "http://localhost:8080/blog"
   ```

## Lizenz

Dieses Projekt steht unter der MIT-Lizenz.

