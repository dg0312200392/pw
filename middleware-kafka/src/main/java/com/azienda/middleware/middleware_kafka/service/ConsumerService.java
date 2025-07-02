/**
 * @file ConsumerService.java
 * @brief Servizio per la ricezione e decifratura di messaggi da Kafka.
 */

package com.azienda.middleware.middleware_kafka.service;

import com.azienda.middleware.middleware_kafka.util.CryptoUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final CryptoUtils cryptoUtils;
    private final AnomalyDetector anomalyDetector = new AnomalyDetector();

    /**
     * Costruttore con iniezione del servizio di cifratura.
     *
     * @param cryptoUtils utility per la cifratura/decifratura
     */
    public ConsumerService(CryptoUtils cryptoUtils) {
        this.cryptoUtils = cryptoUtils;
    }

    /**
     * Listener Kafka per ricevere e decifrare i messaggi.
     *
     * @param encryptedMessage il messaggio ricevuto (cifrato)
     */
    @KafkaListener(topics = "secure-topic", groupId = "azienda-group")
    public void listen(String encryptedMessage) {
        String decrypted = cryptoUtils.decrypt(encryptedMessage);
        System.out.println("Ricevuto messaggio: " + decrypted);

        if (anomalyDetector.isAnomalous(decrypted)) {
            System.out.println("--- Anomalia rilevata! ---");
        }
    }
}
