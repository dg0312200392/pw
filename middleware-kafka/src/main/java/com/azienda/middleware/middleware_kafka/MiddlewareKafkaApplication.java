/**
 * @file MiddlewareKafkaApplication.java
 * @brief Classe principale di avvio per l'applicazione Spring Boot.
 */

package com.azienda.middleware.middleware_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.azienda.middleware.middleware_kafka.config.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MiddlewareKafkaApplication {

    /**
     * Metodo main di avvio dell'applicazione.
     *
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {
        SpringApplication.run(MiddlewareKafkaApplication.class, args);
    }
}
