package com.bicirepair.facturacion_service.service;

import com.bicirepair.facturacion_service.model.Facturacion;
import com.bicirepair.facturacion_service.repository.FacturacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacturacionServiceTest {

    @Mock
    private FacturacionRepository facturacionRepository;

    @InjectMocks
    private FacturacionService facturacionService;

    @Test
    void deberiaEmitirFacturaCorrectamente() {
        Date fecha = new Date(System.currentTimeMillis());
        Facturacion factura = new Facturacion(null, 50L, 35000, "Tarjeta Debito", fecha);
        Facturacion facturaGuardada = new Facturacion(1L, 50L, 35000, "Tarjeta Debito", fecha);
        
        when(facturacionRepository.save(factura)).thenReturn(facturaGuardada);

        Facturacion resultado = facturacionService.guardar(factura);

        assertNotNull(resultado);
        assertEquals(35000, resultado.getCobroTotal());
        verify(facturacionRepository, times(1)).save(factura);
    }
}