package com.bicirepair.facturacion_service.repository;

import com.bicirepair.facturacion_service.model.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {
    List<Facturacion> findByMetodoPago(String metodoPago);
    List<Facturacion> findByFechaFacturaBetween(Date inicio, Date fin);
    List<Facturacion> findByIdReparacion(Long idReparacion);
}