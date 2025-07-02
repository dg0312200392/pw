/**
 * @file SecurityConfig.java
 * @brief Configurazione di sicurezza per l'applicazione Spring Boot.
 * 
 * Disabilita CSRF e permette tutte le richieste HTTP.
 */

package com.azienda.middleware.middleware_kafka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Configura il filtro di sicurezza disabilitando CSRF e permettendo tutte le richieste.
     *
     * @param http oggetto HttpSecurity
     * @return la security filter chain configurata
     * @throws Exception in caso di errore nella configurazione
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
