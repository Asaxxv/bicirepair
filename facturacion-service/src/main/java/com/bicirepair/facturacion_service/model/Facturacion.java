package com.bicirepair.facturacion_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facturacion")
public class Facturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    private Long idReparacion;
    @NotBlank(message = "El total es obligatorio")
    private int cobroTotal;
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;
    @NotBlank(message = "La fecha de la factura es obligatoria")
    private Date fechaFactura;
}