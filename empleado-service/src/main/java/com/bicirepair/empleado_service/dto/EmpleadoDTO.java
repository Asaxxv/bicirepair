package com.bicirepair.empleado_service.dto;

import com.bicirepair.empleado_service.model.Empleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoDTO {

    private int idEmpleado; 
    private String rutEmp;
    private String nombreEmp;
    private String correoEmp;
    private int telefonoEmp;
    private String cargoEmp;
    private Date fechaIngresoEmp;

    public Empleado toModel() {
        return new Empleado(0, rutEmp, nombreEmp, correoEmp, telefonoEmp, cargoEmp, fechaIngresoEmp);
    }

    public static EmpleadoDTO fromModel(Empleado e) {
        if (e == null) return null;
        return new EmpleadoDTO(e.getIdEmpleado(), e.getRutEmp(), e.getNombreEmp(), e.getCorreoEmp(),
                               e.getTelefonoEmp(), e.getCargoEmp(), e.getFechaIngresoEmp());
    }
}