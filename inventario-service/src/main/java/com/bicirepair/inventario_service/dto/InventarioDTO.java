package com.bicirepair.inventario_service.dto;

import com.bicirepair.inventario_service.model.Inventario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioDTO {

    private Long idInventario;
    private Long idProducto;
    private Long idProveedor;
    private String tipoMovimiento;
    private int cantidad;
    private Date fechaMovimiento;

    public Inventario toModel() {
        return new Inventario(this.idInventario, this.idProducto, this.idProveedor, this.tipoMovimiento, this.cantidad, this.fechaMovimiento);
    }

    public static InventarioDTO fromModel(Inventario i) {
        if (i == null) return null;
        return new InventarioDTO(i.getIdInventario(), i.getIdProducto(), i.getIdProveedor(),
                                 i.getTipoMovimiento(), i.getCantidad(), i.getFechaMovimiento());
    }
}