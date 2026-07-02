package com.bicirepair.empleado_service.service;

import com.bicirepair.empleado_service.model.Empleado;
import com.bicirepair.empleado_service.repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    void deberiaGuardarEmpleado() {
        java.sql.Date fechaDefecto = new java.sql.Date(System.currentTimeMillis());
        
        Empleado emp = new Empleado(null, "11223344-5", "Carlos Gómez", "carlos@bici.com", 912345678, "Mecánico Senior", fechaDefecto);
        Empleado empGuardado = new Empleado(1L, "11223344-5", "Carlos Gómez", "carlos@bici.com", 912345678, "Mecánico Senior", fechaDefecto);
        
        when(empleadoRepository.save(emp)).thenReturn(empGuardado);

        Empleado resultado = empleadoService.guardar(emp);

        assertNotNull(resultado);
        assertEquals("Mecánico Senior", resultado.getCargoEmp());
    }
}