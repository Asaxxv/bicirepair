package com.bicirepair.cliente_service.dto;

import com.bicirepair.cliente_service.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long idCliente;
    private String nombreCliente;
    private String rutCliente;
    private String correoCliente;
    private int telefonoCliente;

    public Cliente toModel() {
        return new Cliente(idCliente, rutCliente, nombreCliente, correoCliente, telefonoCliente);
    }

    public static ClienteDTO fromModel(Cliente c) {
        if (c == null) return null;
        return new ClienteDTO(c.getIdCliente(), c.getNombreCliente(), c.getRutCliente(), 
                              c.getCorreoCliente(), c.getTelefonoCliente());
    }
}