package com.bicirepair.proveedor_service.service;

import com.bicirepair.proveedor_service.model.Proveedor;
import com.bicirepair.proveedor_service.repository.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void deberiaGuardarProveedorCorrectamente() {
        Proveedor prov = new Proveedor(null, "BiciWholesale", "77665544-K", "+56988887777", "ventas@biciwholesale.cl", "Santiago Centro");
        Proveedor provGuardado = new Proveedor(1L, "BiciWholesale", "77665544-K", "+56988887777", "ventas@biciwholesale.cl", "Santiago Centro");

        when(proveedorRepository.save(prov)).thenReturn(provGuardado);

        Proveedor resultado = proveedorService.guardar(prov);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProveedor());
    }
}