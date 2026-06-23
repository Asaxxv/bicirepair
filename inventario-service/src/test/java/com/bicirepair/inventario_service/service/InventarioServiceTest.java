package com.bicirepair.inventario_service.service;

import com.bicirepair.inventario_service.model.Inventario;
import com.bicirepair.inventario_service.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void deberiaRegistrarMovimientoInventario() {
        Date fecha = new Date(System.currentTimeMillis());
        Inventario mov = new Inventario(null, 10L, 5L, "INGRESO", 20, fecha);
        Inventario movGuardado = new Inventario(1L, 10L, 5L, "INGRESO", 20, fecha);

        when(inventarioRepository.save(mov)).thenReturn(movGuardado);

        Inventario resultado = inventarioService.guardar(mov);

        assertNotNull(resultado);
        assertEquals(20, resultado.getCantidad());
        assertEquals("INGRESO", resultado.getTipoMovimiento());
    }
}