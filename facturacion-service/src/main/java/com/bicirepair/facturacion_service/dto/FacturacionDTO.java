package com.bicirepair.facturacion_service.dto;

import com.bicirepair.facturacion_service.model.Facturacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturacionDTO {

    private int idFactura;
    private int idReparacion;
    private int cobroTotal;
    private String metodoPago;
    private Date fechaFactura;

    public Facturacion toModel() {
        return new Facturacion(0, idReparacion, cobroTotal, metodoPago, fechaFactura);
    }

    public static FacturacionDTO fromModel(Facturacion f) {
        if (f == null) return null;
        return new FacturacionDTO(f.getIdFactura(), f.getIdReparacion(),
                                  f.getCobroTotal(), f.getMetodoPago(),
                                  f.getFechaFactura());
    }
}