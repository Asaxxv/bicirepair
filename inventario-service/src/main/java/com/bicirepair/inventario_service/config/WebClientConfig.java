package com.bicirepair.inventario_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.producto.base-url}")
    private String productoBaseUrl;

    @Value("${api.proveedor.base-url}")
    private String proveedorBaseUrl;

    @Bean(name = "productoWebClient")
    public WebClient productoWebClient() {
        return WebClient.builder().baseUrl(productoBaseUrl).build();
    }

    @Bean(name = "proveedorWebClient")
    public WebClient proveedorWebClient() {
        return WebClient.builder().baseUrl(proveedorBaseUrl).build();
    }
}