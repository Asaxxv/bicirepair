package com.bicirepair.empleado_service.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionEmpleadoRutTest {

    private boolean validarFormatoRut(String rut) {
        return rut != null && rut.matches("\\d{7,8}-[\\dkK]");
    }

    private boolean esMayorDeEdad(LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        if (fechaNacimiento == null || fechaIngreso == null) return false;
        long anos = ChronoUnit.YEARS.between(fechaNacimiento, fechaIngreso);
        return anos >= 18;
    }

    @Test
    void deberiaValidarRutYEdadCorrectamente() {
        // Given
        String rutEmpleado = "18456123-9";
        LocalDate nacimiento = LocalDate.of(2000, 5, 12);
        LocalDate ingresoLaboral = LocalDate.of(2025, 3, 1); // Tiene 24 años al entrar

        // When & Then
        assertTrue(validarFormatoRut(rutEmpleado));
        assertTrue(esMayorDeEdad(nacimiento, ingresoLaboral));
    }

    @Test
    void deberiaRechazarEmpleadoMenorDeEdad() {
        // Given
        LocalDate nacimiento = LocalDate.of(2012, 1, 1);
        LocalDate ingresoLaboral = LocalDate.of(2026, 6, 23); // Tiene 14 años en 2026

        // When & Then
        assertFalse(esMayorDeEdad(nacimiento, ingresoLaboral));
    }
}