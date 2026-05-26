package com.bicirepair.reparacion_service.dto;

import com.bicirepair.reparacion_service.model.Reparacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReparacionDTO {

    private Long idReparacion;
    private Long idBicicleta;
    private Long idEmpleado;
    private String descripcionReparacion;
    private int costoTotal;
    private Date fechaReparacion;
    private String estadoReparacion;

    public Reparacion toModel() {
        return new Reparacion(idReparacion, idBicicleta, idEmpleado, descripcionReparacion,
                              costoTotal, fechaReparacion, estadoReparacion);
    }

    public static ReparacionDTO fromModel(Reparacion r) {
        if (r == null) return null;
        return new ReparacionDTO(r.getIdReparacion(), r.getIdBicicleta(), r.getIdEmpleado(),
                                 r.getDescripcionReparacion(), r.getCostoTotal(),
                                 r.getFechaReparacion(), r.getEstadoReparacion());
    }
}