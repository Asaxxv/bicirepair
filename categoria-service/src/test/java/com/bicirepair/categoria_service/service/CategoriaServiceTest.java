package com.bicirepair.categoria_service.service;

import com.bicirepair.categoria_service.model.Categoria;
import com.bicirepair.categoria_service.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void deberiaCrearCategoria() {
        Categoria cat = new Categoria(null, "Frenos");
        Categoria catGuardada = new Categoria(1L, "Frenos");
        when(categoriaRepository.save(cat)).thenReturn(catGuardada);

        Categoria resultado = categoriaService.guardar(cat);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCategoria());
    }
}