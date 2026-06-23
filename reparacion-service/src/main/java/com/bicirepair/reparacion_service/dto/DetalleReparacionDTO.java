package com.bicirepair.reparacion_service.dto;

import com.bicirepair.reparacion_service.model.DetalleReparacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleReparacionDTO {

    private Long idDetalle;
    private Long idReparacion;
    private Long idProducto;
    private int cantidad;
    private int precioUnitario;

    public DetalleReparacion toModel() {
        return new DetalleReparacion(this.idDetalle, this.idReparacion, this.idProducto, this.cantidad, this.precioUnitario);
    }

    public static DetalleReparacionDTO fromModel(DetalleReparacion d) {
        if (d == null) return null;
        return new DetalleReparacionDTO(d.getIdDetalle(), d.getIdReparacion(),
                                        d.getIdProducto(), d.getCantidad(),
                                        d.getPrecioUnitario());
    }
}