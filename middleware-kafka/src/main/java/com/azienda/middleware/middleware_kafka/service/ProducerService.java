/**
 * @file ProducerService.java
 * @brief Servizio per l'invio di messaggi cifrati tramite Kafka.
 */

package com.azienda.middleware.middleware_kafka.service;

import com.azienda.middleware.middleware_kafka.util.CryptoUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final CryptoUtils cryptoUtils;
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Costruttore con iniezione delle dipendenze.
     *
     * @param cryptoUtils utility per cifrare i messaggi
     * @param kafkaTemplate template Kafka per inviare messaggi
     */
    public ProducerService(CryptoUtils cryptoUtils, KafkaTemplate<String, String> kafkaTemplate) {
        this.cryptoUtils = cryptoUtils;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Cifra e invia un messaggio sul topic Kafka.
     *
     * @param plainText il messaggio in chiaro da cifrare e inviare
     */
    public void sendEncryptedMessage(String plainText) {
        String encrypted = cryptoUtils.encrypt(plainText);
        kafkaTemplate.send("secure-topic", encrypted);
    }
}
