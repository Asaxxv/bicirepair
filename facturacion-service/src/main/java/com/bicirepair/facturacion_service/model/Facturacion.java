package com.bicirepair.facturacion_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facturacion")
public class Facturacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;

    private int idReparacion;
    private int cobroTotal;
    private String metodoPago;
    private Date fechaFactura;
}