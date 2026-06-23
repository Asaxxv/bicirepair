package com.bicirepair.notificacion_service.repository;

import com.bicirepair.notificacion_service.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByCanal(String canal);
    List<Notificacion> findByFechaEnvioBetween(LocalDate inicio, LocalDate fin);
    List<Notificacion> findByIdReparacion(Long idReparacion);
}