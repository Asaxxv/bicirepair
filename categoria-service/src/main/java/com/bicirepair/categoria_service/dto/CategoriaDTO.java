package com.bicirepair.categoria_service.dto;

import com.bicirepair.categoria_service.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {

    private int idCategoria;
    private String nombreCategoria;

    public Categoria toModel() {
        return new Categoria(0, nombreCategoria);
    }

    public static CategoriaDTO fromModel(Categoria c) {
        if (c == null) return null;
        return new CategoriaDTO(c.getIdCategoria(), c.getNombreCategoria());
    }
}