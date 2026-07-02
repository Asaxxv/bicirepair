package com.bicirepair.facturacion_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculoImpuestoTest {

    @Test
    void deberiaCalcularIvaCorrectamente() {
        // Given - Un cobro total de 50.000 pesos
        int cobroTotal = 50000;

        // When - Calculamos el IVA (19%) y el valor Neto
        int neto = (int) Math.round(cobroTotal / 1.19);
        int iva = cobroTotal - neto;

        // Then
        assertEquals(42017, neto); // 50000 / 1.19
        assertEquals(7983, iva);   // 50000 - 42017
        assertEquals(cobroTotal, neto + iva);
    }
}