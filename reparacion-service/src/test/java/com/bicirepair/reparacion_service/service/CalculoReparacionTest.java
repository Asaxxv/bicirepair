package com.bicirepair.reparacion_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculoReparacionTest {

    @Test
    void deberiaCalcularCostoTotalDeReparacionConDetalles() {
        // Given
        int precioUnitario1 = 7500, cantidad1 = 2;
        int precioUnitario2 = 5000, cantidad2 = 1;

        // When
        int total = (precioUnitario1 * cantidad1) + (precioUnitario2 * cantidad2);

        // Then
        assertEquals(20000, total);
    }
}