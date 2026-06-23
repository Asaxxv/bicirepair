package com.bicirepair.empleado_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI empleadoServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Empleado Service API")
                        .description("Gestión de empleados de BICIREPAIR")
                        .version("1.0"));
    }
}