package com.bicirepair.inventario_service.repository;

import com.bicirepair.inventario_service.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByTipoMovimiento(String tipoMovimiento);
    List<Inventario> findByFechaMovimientoBetween(LocalDate inicio, LocalDate fin);
    List<Inventario> findByIdProducto(Long idProducto);
    List<Inventario> findByIdProveedor(Long idProveedor);
}