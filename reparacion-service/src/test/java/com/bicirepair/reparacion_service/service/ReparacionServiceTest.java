package com.bicirepair.reparacion_service.service;

import com.bicirepair.reparacion_service.model.Reparacion;
import com.bicirepair.reparacion_service.repository.ReparacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReparacionServiceTest {

    @Mock
    private ReparacionRepository reparacionRepository;

    @InjectMocks
    private ReparacionService reparacionService;

    @Test
    void deberiaRegistrarUnaReparacion() {
        java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());
        
        Reparacion rep = new Reparacion(null, 1L, 3L, "Mantenimiento completo y purgado de frenos", 25000, fecha, "EN_PROCESO");
        Reparacion repGuardada = new Reparacion(1L, 1L, 3L, "Mantenimiento completo y purgado de frenos", 25000, fecha, "EN_PROCESO");

        when(reparacionRepository.save(rep)).thenReturn(repGuardada);

        Reparacion resultado = reparacionService.guardar(rep);

        assertNotNull(resultado);
        assertEquals("EN_PROCESO", resultado.getEstadoReparacion());
        verify(reparacionRepository, times(1)).save(rep);
    }
}