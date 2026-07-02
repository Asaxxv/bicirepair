package com.bicirepair.notificacion_service.service;

import com.bicirepair.notificacion_service.model.Notificacion;
import com.bicirepair.notificacion_service.repository.NotificacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @Test
    void deberiaEnviarYRegistrarNotificacion() {
        java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());
        
        Notificacion noti = new Notificacion(null, 1L, "Su bicicleta está lista para retiro", "EMAIL", false, fecha);
        Notificacion notiGuardada = new Notificacion(1L, 1L, "Su bicicleta está lista para retiro", "EMAIL", true, fecha);

        when(notificacionRepository.save(noti)).thenReturn(notiGuardada);

        Notificacion resultado = notificacionService.guardar(noti);

        assertNotNull(resultado);
        assertEquals("EMAIL", resultado.getCanal()); 
    }
}