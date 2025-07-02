/**
 * @file AnomalyDetector.java
 * @brief Classe per il rilevamento di messaggi anomali.
 * 
 * Rileva messaggi che contengono parole chiave di allarme o che superano una certa lunghezza.
 */

package com.azienda.middleware.middleware_kafka.service;

public class AnomalyDetector {

    /**
     * Verifica se il messaggio è considerato anomalo.
     *
     * @param message il messaggio da analizzare
     * @return true se il messaggio è anomalo, false altrimenti
     */
    public boolean isAnomalous(String message) {
        return message.contains("ALLARME") || message.length() > 200;
    }
}
