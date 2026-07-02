package com.bicirepair.categoria_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI categoriaServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Categoría Service API")
                        .description("Gestión de categorías de BICIREPAIR")
                        .version("1.0"));
    }
}