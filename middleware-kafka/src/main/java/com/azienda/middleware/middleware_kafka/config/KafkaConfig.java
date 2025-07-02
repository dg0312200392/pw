/**
 * @file KafkaConfig.java
 * @brief Configurazione dei topic Kafka per l'applicazione.
 */

package com.azienda.middleware.middleware_kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    /**
     * Crea il topic Kafka "secure-topic" se non esiste.
     *
     * @return un oggetto NewTopic con nome, partizioni e fattore di replica
     */
    @Bean
    public NewTopic secureTopic() {
        return new NewTopic("secure-topic", 1, (short) 1);
    }
}
