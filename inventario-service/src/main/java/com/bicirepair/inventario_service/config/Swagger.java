package com.bicirepair.inventario_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI inventarioServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventario Service API")
                        .description("Gestión de inventario de BICIREPAIR")
                        .version("1.0"));
    }
}