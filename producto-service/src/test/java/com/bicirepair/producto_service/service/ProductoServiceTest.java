package com.bicirepair.producto_service.service;

import com.bicirepair.producto_service.model.Producto;
import com.bicirepair.producto_service.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void deberiaGuardarProducto() {
        Producto prod = new Producto(null, "Cadena Shimano 11v", 25000, 15, 2L);
        Producto prodGuardado = new Producto(1L, "Cadena Shimano 11v", 25000, 15, 2L);

        when(productoRepository.save(prod)).thenReturn(prodGuardado);

        Producto resultado = productoService.guardar(prod);

        assertNotNull(resultado);
        assertEquals("Cadena Shimano 11v", resultado.getNombreProducto());
    }
}