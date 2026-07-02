package com.bicirepair.cliente_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionRutTest {

    // Método de soporte para evaluar la expresión regular del RUT Chileno
    private boolean validarFormatoRut(String rut) {
        return rut != null && rut.matches("\\d{7,8}-[\\dkK]");
    }

    @Test
    void deberiaValidarRutConFormatoCorrecto() {
        // Given
        String rutValido = "12345678-9";

        // When
        boolean resultado = validarFormatoRut(rutValido);

        // Then
        assertTrue(resultado);
    }

    @Test
    void deberiaRechazarRutConFormatoIncorrecto() {
        // Given
        String rutInvalido = "12345678";

        // When
        boolean resultado = validarFormatoRut(rutInvalido);

        // Then
        assertFalse(resultado);
    }

    @Test
    void deberiaValidarRutConDigitoK() {
        // Given - Caso de borde común en Chile
        String rutConK = "1234567-K";

        // When
        boolean resultado = validarFormatoRut(rutConK);

        // Then
        assertTrue(resultado);
    }
}