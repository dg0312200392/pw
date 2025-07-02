# Istruzioni per l'esecuzione del middleware Kafka e dello script di test

- Installare Docker Desktop
- Da PowerShell o terminale, posizionarsi nella cartella dove c’è il file "docker-compose.yml" ed eseguire il comando: "docker-compose up -d"
- Attendere che i container si avviino (è possibile verificare tramite il comando "docker ps")
- Eseguire l'app SpringBoot dal file "MiddlewareKafkaApplication.java".
- Eseguire lo stress test del middleware dal file "StressTest.java"
