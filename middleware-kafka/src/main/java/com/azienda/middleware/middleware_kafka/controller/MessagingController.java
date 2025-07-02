/**
 * @file MessagingController.java
 * @brief Controller REST per inviare messaggi criptati tramite Kafka.
 */

package com.azienda.middleware.middleware_kafka.controller;

import com.azienda.middleware.middleware_kafka.service.ProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessagingController {

    private final ProducerService producerService;

    /**
     * Costruttore per l'iniezione del ProducerService.
     *
     * @param producerService il servizio per inviare messaggi
     */
    public MessagingController(ProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * Endpoint POST per inviare un messaggio.
     *
     * @param message il messaggio in chiaro da inviare
     * @return conferma dell'invio
     */
    @PostMapping
    public String sendMessage(@RequestParam String message) {
        producerService.sendEncryptedMessage(message);
        return "Messaggio inviato!";
    }
}
