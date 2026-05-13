package com.bicirepair.reparacion_service.repository;

import com.bicirepair.reparacion_service.model.DetalleReparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleReparacionRepository extends JpaRepository<DetalleReparacion, Integer> {
    List<DetalleReparacion> findByIdReparacion(int idReparacion);
}