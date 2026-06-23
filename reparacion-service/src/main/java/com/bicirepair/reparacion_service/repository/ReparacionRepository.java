package com.bicirepair.reparacion_service.repository;

import com.bicirepair.reparacion_service.model.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Long> {
    List<Reparacion> findByEstadoReparacion(String estadoReparacion);
    List<Reparacion> findByFechaReparacionBetween(Date inicio, Date fin);
    List<Reparacion> findByIdEmpleado(Long idEmpleado);
}