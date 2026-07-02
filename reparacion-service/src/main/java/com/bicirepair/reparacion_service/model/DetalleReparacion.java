package com.bicirepair.reparacion_service.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "detalle_reparacion")
public class DetalleReparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    private Long idReparacion;    // referencia plana, sin @ManyToOne
    private Long idProducto;
    private int cantidad;
    private int precioUnitario;
 }
