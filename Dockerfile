FROM eclipse-temurin:17-jdk AS build

# Arbeitsverzeichnis setzen
WORKDIR /app

# Projektdateien kopieren
COPY . .

# Maven Wrapper ausführbar machen
RUN chmod +x mvnw

# Maven Build ausführen
RUN ./mvnw package -DskipTests

# --------- Laufzeitumgebung ---------
FROM eclipse-temurin:17-jre

# Arbeitsverzeichnis setzen
WORKDIR /app

# Das gebaute JAR aus dem vorherigen Schritt kopieren
COPY --from=build /app/target/*-runner.jar app.jar

# Port definieren (falls nötig)
EXPOSE 8081

# Startbefehl für den Container
CMD ["java", "-jar", "app.jar"]
