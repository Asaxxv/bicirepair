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

    private int idBicicleta;
    private String marca;
    private String modelo;
    private String color;
    private int idCliente;

    public Bicicleta toModel() {
        return new Bicicleta(0, marca, modelo, color, idCliente);
    }

    public static BicicletaDTO fromModel(Bicicleta b) {
        if (b == null) return null;
        return new BicicletaDTO(b.getIdBicicleta(), b.getMarca(), b.getModelo(),
                                b.getColor(), b.getIdCliente());
    }
}