package com.bicirepair.reparacion_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI reparacionServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Reparación Service API")
                        .description("Gestión de reparaciones de BICIREPAIR")
                        .version("1.0"));
    }
}