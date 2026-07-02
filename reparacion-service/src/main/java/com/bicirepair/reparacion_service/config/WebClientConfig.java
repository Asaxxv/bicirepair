package com.bicirepair.reparacion_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.bicicleta.base-url}")
    private String bicicletaBaseUrl;

    @Value("${api.empleado.base-url}")
    private String empleadoBaseUrl;

    @Bean(name = "bicicletaWebClient")
    public WebClient bicicletaWebClient() {
        return WebClient.builder()
                .baseUrl(bicicletaBaseUrl)
                .build();
    }

    @Bean(name = "empleadoWebClient")
    public WebClient empleadoWebClient() {
        return WebClient.builder()
                .baseUrl(empleadoBaseUrl)
                .build();
    }
}