package com.bicirepair.proveedor_service.dto;

import com.bicirepair.proveedor_service.model.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {

    private Long idProveedor;
    private String nombreProveedor;
    private String rutProveedor;
    private String telefonoProveedor;
    private String correoProveedor;
    private String direccionProveedor;

    public Proveedor toModel() {
        return new Proveedor(this.idProveedor, this.nombreProveedor, this.rutProveedor, this.telefonoProveedor,
                             this.correoProveedor, this.direccionProveedor);
    }

    public static ProveedorDTO fromModel(Proveedor p) {
        if (p == null) return null;
        return new ProveedorDTO(p.getIdProveedor(), p.getNombreProveedor(),
                                p.getRutProveedor(), p.getTelefonoProveedor(),
                                p.getCorreoProveedor(), p.getDireccionProveedor());
    }
}