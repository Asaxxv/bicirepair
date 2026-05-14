package com.bicirepair.empleado_service.repository;

import com.bicirepair.empleado_service.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByNombreEmp(String nombreEmp);;
    List<Empleado> findByCargoEmp(String cargoEmp);;
    List<Empleado> findByRutEmp(String rutEmp);
}