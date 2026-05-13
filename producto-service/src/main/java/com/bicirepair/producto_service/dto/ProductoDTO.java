package com.bicirepair.producto_service.dto;

import com.bicirepair.producto_service.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private int idProducto;
    private String nombreProducto;
    private int precio;
    private int cantidad;
    private int idCategoria;

    public Producto toModel() {
        return new Producto(0, nombreProducto, precio, cantidad, idCategoria);
    }

    public static ProductoDTO fromModel(Producto p) {
        if (p == null) return null;
        return new ProductoDTO(p.getIdProducto(), p.getNombreProducto(), p.getPrecio(),
                               p.getCantidad(), p.getIdCategoria());
    }
}
