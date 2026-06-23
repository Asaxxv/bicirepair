package com.bicirepair.notificacion_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormatoMensajeTest {

    private String construirMensajeRetiro(String nombreCliente, Long idReparacion) {
        return String.format("Hola %s, tu bicicleta de la orden N°%d ya está lista para ser retirada.", 
                nombreCliente, idReparacion);
    }

    @Test
    void deberiaArmarMensajeDeRetiroCorrectamente() {
        // Given
        String cliente = "Don Gato";
        Long reparacionId = 1045L;

        // When
        String mensajeFinal = construirMensajeRetiro(cliente, reparacionId);

        // Then
        String esperado = "Hola Don Gato, tu bicicleta de la orden N°1045 ya está lista para ser retirada.";
        assertEquals(esperado, mensajeFinal);
    }
}