package com.bicirepair.proveedor_service.repository;

import com.bicirepair.proveedor_service.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByNombreProveedor(String nombreProveedor);
    List<Proveedor> findByRutProveedor(String rutProveedor);
}