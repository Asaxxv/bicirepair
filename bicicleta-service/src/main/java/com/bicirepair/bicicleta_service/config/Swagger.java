package com.bicirepair.bicicleta_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI bicicletaServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bicicleta Service API")
                        .description("Gestión de bicicletas de BICIREPAIR")
                        .version("1.0"));
    }
}