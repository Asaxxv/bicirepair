package com.bicirepair.notificacion_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.reparacion.base-url}")
    private String reparacionBaseUrl;

    @Bean
    public WebClient reparacionWebClient() {
        return WebClient.builder().baseUrl(reparacionBaseUrl).build();
    }
}