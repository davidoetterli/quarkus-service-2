# Quarkus Microservices Projekt

Dieses Projekt besteht aus zwei Quarkus-Services, die über Kafka miteinander kommunizieren.

## 📌 Voraussetzungen

Bevor du die Services startest, stelle sicher, dass folgende Voraussetzungen erfüllt sind:

- **Java 17 oder höher** ist installiert
- **Maven** ist installiert
- **Docker** ist installiert und läuft (für Kafka und die Datenbank erforderlich)

## 📦 Projektübersicht

- **quarkus-service-1**: Stellt eine REST API bereit, um Blog-Posts zu erstellen und sendet eine Validierungsanfrage an Kafka.
- **quarkus-service-2**: Hört auf die Validierungsanfragen, verarbeitet sie und sendet eine Antwort zurück.

## 🚀 Services starten

### 1️⃣ Starte Kafka & MySQL mit Docker

Falls du Kafka und MySQL nicht lokal installiert hast, kannst du sie mit Docker starten:

```sh
# Starte Kafka mit Docker
docker-compose up -d
```

Falls du kein `docker-compose.yml` hast, erstelle einen oder installiere Kafka manuell.

### 2️⃣ Starte `quarkus-service-1`

Öffne ein Terminal und navigiere in das Verzeichnis des ersten Services:

```sh
cd quarkus-service-1
./mvnw quarkus:dev
```

### 3️⃣ Starte `quarkus-service-2`

Öffne ein weiteres Terminal und navigiere in das Verzeichnis des zweiten Services:

```sh
cd quarkus-service-2
./mvnw quarkus:dev
```

### 4️⃣ Teste die Services

Sobald beide Services laufen, kannst du einen Blog-Post erstellen und die Validierung testen.

#### Blog-Post erstellen:
```sh
curl -X POST "http://localhost:8080/blog" \
     -H "Content-Type: application/json" \
     -d '{"title": "Mein Blogpost", "content": "Dies ist ein Test"}'
```

Der `quarkus-service-1` sendet eine Validierungsanfrage an Kafka, und `quarkus-service-2` verarbeitet sie.

### 5️⃣ Logs und Debugging
Falls es Probleme gibt, überprüfe die Logs beider Services.

- **Für `quarkus-service-1`**:
  ```sh
  tail -f quarkus-service-1/logs/quarkus.log
  ```
- **Für `quarkus-service-2`**:
  ```sh
  tail -f quarkus-service-2/logs/quarkus.log
  ```

Falls Docker nicht richtig läuft, stelle sicher, dass der `Kafka`- und `MySQL`-Container aktiv sind:
```sh
docker ps
```

## ✅ Fertig!

Wenn alles erfolgreich läuft, hast du zwei Quarkus-Services, die über Kafka kommunizieren und Blogposts validieren.