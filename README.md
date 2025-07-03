
# Middleware Kafka - Istruzioni di Esecuzione

Questo progetto implementa un middleware basato su Kafka, sviluppato con Spring Boot, corredato da uno script di stress test.

---

## Prerequisiti

- **Docker Desktop** installato e in esecuzione  
- **Java 17+** (o versione compatibile con Spring Boot usato)  
- **Maven 3.8+** (per compilare ed eseguire il progetto)  
- **Visual Studio Code** (o un altro IDE Java) 
---

## Avvio dell’ambiente Kafka con Docker

1. Apri PowerShell o il terminale  
2. Posizionati nella cartella contenente il file `docker-compose.yml`:

   ```
   cd /percorso_della_cartella
   ```

3. Esegui il comando per avviare i container Kafka e relativi servizi in background:

   ```
   docker-compose up -d
   ```

4. Attendi che i container siano attivi e funzionanti. Puoi verificarlo con:

   ```
   docker ps
   ```

---

## Esecuzione dell’app Spring Boot

Puoi avviare l’applicazione principale che funge da middleware Kafka eseguendo la classe:

- `MiddlewareKafkaApplication.java`

Da IDE o da terminale con Maven:

```
mvn spring-boot:run
```

oppure (dopo la build):

```
java -jar target/middleware-kafka.jar
```

---

## Esecuzione dello Stress Test

Per testare il middleware con carico simulato, esegui la classe:

- `StressTest.java`

Anche questa può essere eseguita da IDE o tramite Maven.

---

## Struttura del progetto

- `/src/main/java` — codice sorgente Java  
- `/src/main/resources` — configurazioni e risorse  
- `/docs` — documentazione html del progetto + diagramma UML  

---

