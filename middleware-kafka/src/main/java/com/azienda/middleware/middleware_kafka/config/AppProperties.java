/**
 * @file AppProperties.java
 * @brief Configurazione delle proprietà applicative personalizzate.
 * 
 * Classe che rappresenta le proprietà definite nel file application.yml o application.properties
 * con prefisso "app". Utilizzata per recuperare la chiave AES.
 */

package com.azienda.middleware.middleware_kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * Chiave AES utilizzata per la cifratura/decifratura dei messaggi.
     */
    private String aesKey;

    /**
     * @return la chiave AES
     */
    public String getAesKey() {
        return aesKey;
    }

    /**
     * @param aesKey la chiave AES da impostare
     */
    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
