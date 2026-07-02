package com.bicirepair.proveedor_service.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionContactoProveedorTest {

    private boolean validarCorreoProveedor(String correo) {
        return correo != null && correo.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean validarTelefonoChileno(String telefono) {
        // Valida formatos comunes chilenos como +56912345678 o 912345678
        return telefono != null && telefono.matches("^(\\+?56)?9\\d{8}$");
    }

    @Test
    void deberiaAceptarCorreoValido() {
        assertTrue(validarCorreoProveedor("contacto@bicipartes.cl"));
        assertFalse(validarCorreoProveedor("correo-invalido.com"));
    }

    @Test
    void deberiaValidarTelefonoFormatoChileno() {
        assertTrue(validarTelefonoChileno("+56912345678"));
        assertTrue(validarTelefonoChileno("987654321"));
        assertFalse(validarTelefonoChileno("12345")); // Muy corto
    }
}