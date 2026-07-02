package com.bicirepair.inventario_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControlStockTest {

    private int calcularNuevoStock(int stockActual, String tipoMovimiento, int cantidad) {
        if ("INGRESO".equalsIgnoreCase(tipoMovimiento)) {
            return stockActual + cantidad;
        } else if ("EGRESO".equalsIgnoreCase(tipoMovimiento)) {
            return stockActual - cantidad;
        }
        return stockActual;
    }

    @Test
    void deberiaSumarStockEnIngreso() {
        // Given
        int stockInicial = 10;
        int cantidadMovimiento = 5;

        // When
        int stockFinal = calcularNuevoStock(stockInicial, "INGRESO", cantidadMovimiento);

        // Then
        assertEquals(15, stockFinal);
    }

    @Test
    void deberiaRestarStockEnEgreso() {
        // Given
        int stockInicial = 10;
        int cantidadMovimiento = 3;

        // When
        int stockFinal = calcularNuevoStock(stockInicial, "EGRESO", cantidadMovimiento);

        // Then
        assertEquals(7, stockFinal);
    }
}