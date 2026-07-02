package com.bicirepair.proveedor_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionRutEmpresaTest {

    private boolean validarRutEmpresa(String rut) {
        if (rut == null || !rut.matches("\\d{7,8}-[\\dkK]")) {
            return false;
        }
        
        // Extraemos los números antes del guion
        String parteNumerica = rut.split("-")[0];
        int numeroRut = Integer.parseInt(parteNumerica);
        
        // Las empresas en Chile típicamente tienen RUT sobre los 50.000.000
        return numeroRut >= 50000000;
    }

    @Test
    void deberiaAceptarRutValidoDeEmpresa() {
        // Given - RUT típico de persona jurídica (ej: 76.123.456-K)
        String rutProveedor = "76123456-K";

        // When
        boolean esEmpresa = validarRutEmpresa(rutProveedor);

        // Then
        assertTrue(esEmpresa);
    }

    @Test
    void deberiaRechazarRutSiEsDePersonaNatural() {
        // Given - Un RUT bajo (ej: 12.345.678-9) que pertenece a una persona natural
        String rutPersona = "12345678-9";

        // When
        boolean esEmpresa = validarRutEmpresa(rutPersona);

        // Then
        assertFalse(esEmpresa); // Falla la regla de negocio de proveedores corporativos
    }
}