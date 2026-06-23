package com.bicirepair.cliente_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI clienteServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cliente Service API")
                        .description("Gestión de clientes de BICIREPAIR")
                        .version("1.0"));
    }
}