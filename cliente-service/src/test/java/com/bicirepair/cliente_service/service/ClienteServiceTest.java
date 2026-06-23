package com.bicirepair.cliente_service.service;

import com.bicirepair.cliente_service.model.Cliente;
import com.bicirepair.cliente_service.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository; // Mockito simula el repositorio

    @InjectMocks
    private ClienteService clienteService; // Inyecta el mock en tu servicio real

    @Test
    void deberiaGuardarClienteCorrectamente() {
        // Given
        // El objeto que envías a guardar suele ir sin ID (null) si esperas que se autogenere,
        // o con 0L/1L si simulas el flujo completo. Usemos el estándar de tu modelo:
        Cliente cliente = new Cliente(null, "Juan Pérez", "12345678-9", "juan@mail.com", 912345678);
        Cliente clienteGuardado = new Cliente(1L, "Juan Pérez", "12345678-9", "juan@mail.com", 912345678);
        
        when(clienteRepository.save(cliente)).thenReturn(clienteGuardado);

        // When
        Cliente resultado = clienteService.guardar(cliente);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCliente()); // Comparamos contra un Long (1L)
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deberiaListarTodosLosClientes() {
        // Given
        List<Cliente> clientes = List.of(
                new Cliente(1L, "Juan Pérez", "12345678-9", "juan@mail.com", 912345678),
                new Cliente(2L, "María González", "98765432-1", "maria@mail.com", 987654321)
        );
        when(clienteRepository.findAll()).thenReturn(clientes);

        // When
        List<Cliente> resultado = clienteService.listar();

        // Then
        assertEquals(2, resultado.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void deberiaRetornarNullSiClienteNoExisteAlActualizar() {
        // Given
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());
        Cliente clienteNuevo = new Cliente(99L, "Juan Pérez", "12345678-9", "juan@mail.com", 912345678);

        // When
        Cliente resultado = clienteService.actualizar(99L, clienteNuevo);

        // Then
        assertNull(resultado);
    }

    @Test
    void deberiaEliminarClienteExistente() {
        // Given
        when(clienteRepository.existsById(1L)).thenReturn(true);

        // When
        boolean resultado = clienteService.eliminar(1L);

        // Then
        assertTrue(resultado);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void noDeberiaEliminarClienteInexistente() {
        // Given
        when(clienteRepository.existsById(99L)).thenReturn(false);

        // When
        boolean resultado = clienteService.eliminar(99L);

        // Then
        assertFalse(resultado); // Corregido el "falseString" por el método oficial assertFalse
        verify(clienteRepository, never()).deleteById(99L);
    }
}