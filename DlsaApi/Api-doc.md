## Local Docker

Local docker buid: 
```bash
docker build --progress=plain -t dlsa -f DlsaApi/Dockerfile.Api .
```

Local docker run (Local Image): 
```bash
docker run --rm --env SPRING_DATASOURCE_URL="jdbc:mysql://139.174.76.66:3306/dlsa_db?allowPublicKeyRetrieval=true&useSSL=false" -p 5005:8080 dlsa
```

## Docker: Remote Image - Docker Compose

Docker run (Remote Image): 
```bash
docker run --rm --env SPRING_DATASOURCE_URL="jdbc:mysql://139.174.76.66:3306/dlsa_db?allowPublicKeyRetrieval=true&useSSL=false" -p 5005:8080 docker.gitlab.gwdg.de/maria.skrabalekova/dlsa_game:latest
```

Docker-Compose (Remote Image):
```bash
cd DlsaApi
```
```bash
docker-compose up -d
```

## Maven & Java

Local maven build: 
```bash
mvn clean install "-DskipTests"
```

Local java run: 
```bash
java -jar target/DlsaApi-0.0.1-SNAPSHOT.jar
```

## Java Doc generieren

Mit Maven: 
```bash
mvn javadoc:javadoc
```
Ergebnis: "target/site/apidocs/index.html"