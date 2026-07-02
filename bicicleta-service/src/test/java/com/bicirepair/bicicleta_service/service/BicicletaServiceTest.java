package com.bicirepair.bicicleta_service.service;

import com.bicirepair.bicicleta_service.model.Bicicleta;
import com.bicirepair.bicicleta_service.repository.BicicletaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BicicletaServiceTest {

    @Mock
    private BicicletaRepository bicicletaRepository;

    @InjectMocks
    private BicicletaService bicicletaService;

    @Test
    void deberiaGuardarBicicletaCorrectamente() {
        Bicicleta bici = new Bicicleta(null, "Trek", "Marlin 7", "Montaña", 1L);
        Bicicleta biciGuardada = new Bicicleta(1L, "Trek", "Marlin 7", "Montaña", 1L);
        when(bicicletaRepository.save(bici)).thenReturn(biciGuardada);

        Bicicleta resultado = bicicletaService.guardar(bici);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBicicleta());
        verify(bicicletaRepository, times(1)).save(bici);
    }

    @Test
    void deberiaObtenerBicicletaPorId() {
        Bicicleta bici = new Bicicleta(1L, "Trek", "Marlin 7", "Montaña", 1L);
        when(bicicletaRepository.findById(1L)).thenReturn(Optional.of(bici));

        Bicicleta resultado = bicicletaService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Trek", resultado.getMarca());
    }
}