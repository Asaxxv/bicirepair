package com.bicirepair.notificacion_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.bicirepair.notificacion_service.model.Notificacion;
import com.bicirepair.notificacion_service.repository.NotificacionRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacionService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacionService.class);

    private final NotificacionRepository notificacionRepository;
    private final WebClient reparacionWebClient;

    @Value("${api.reparacion.exists}")
    private String reparacionExistsPath;

    public NotificacionService(NotificacionRepository notificacionRepository,
                               WebClient reparacionWebClient) {
        this.notificacionRepository = notificacionRepository;
        this.reparacionWebClient = reparacionWebClient;
    }

    public Notificacion guardar(Notificacion notif) {
        logger.info("Iniciando guardado de notificacion: idReparacion={}", notif.getIdReparacion());

        Boolean existeReparacion;
        try {
            existeReparacion = reparacionWebClient.get()
                    .uri(String.format(reparacionExistsPath, notif.getIdReparacion()))
                    .retrieve().bodyToMono(Boolean.class).block();
        } catch (Exception e) {
            logger.error("Error al validar reparacion id={}", notif.getIdReparacion(), e);
            throw new RuntimeException("Error al validar reparacion");
        }

        if (Boolean.FALSE.equals(existeReparacion)) {
            logger.warn("Reparacion no existe id={}", notif.getIdReparacion());
            throw new RuntimeException("La reparacion con id " + notif.getIdReparacion() + " no existe");
        }

        Notificacion guardada = notificacionRepository.save(notif);
        logger.info("Notificacion guardada con id={}", guardada.getIdNotificacion());
        return guardada;
    }

    public boolean existeId(Long idNotificacion) {
        return notificacionRepository.existsById(idNotificacion);
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public Notificacion obtenerPorId(Long idNotificacion) {
        return notificacionRepository.findById(idNotificacion).orElse(null);
    }

    public Notificacion actualizar(Long id, Notificacion notifNueva) {
        return notificacionRepository.findById(id).map(notifExistente -> {
            notifExistente.setIdReparacion(notifNueva.getIdReparacion());
            notifExistente.setMensaje(notifNueva.getMensaje());
            notifExistente.setCanal(notifNueva.getCanal());
            notifExistente.setEnviada(notifNueva.isEnviada());
            notifExistente.setFechaEnvio(notifNueva.getFechaEnvio());
            return notificacionRepository.save(notifExistente);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (!notificacionRepository.existsById(id)) return false;
        notificacionRepository.deleteById(id);
        return true;
    }

    public List<Notificacion> buscarPorCanal(String canal) {
        return notificacionRepository.findByCanal(canal);
    }

    public List<Notificacion> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return notificacionRepository.findByFechaEnvioBetween(inicio, fin);
    }

    public List<Notificacion> buscarPorIdReparacion(Long idReparacion) {
        return notificacionRepository.findByIdReparacion(idReparacion);
    }
}