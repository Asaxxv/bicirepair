package com.bicirepair.bicicleta_service.dto;

import com.bicirepair.bicicleta_service.model.Bicicleta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BicicletaDTO {

    private Long idBicicleta;
    private String marca;
    private String modelo;
    private String color;
    private Long idCliente;

    public Bicicleta toModel() {
        return new Bicicleta(this.idBicicleta, this.marca, this.modelo, this.color, this.idCliente);
    }

    public static BicicletaDTO fromModel(Bicicleta b) {
        if (b == null) return null;
        return new BicicletaDTO(b.getIdBicicleta(), b.getMarca(), b.getModelo(),
                                b.getColor(), b.getIdCliente());
    }
}