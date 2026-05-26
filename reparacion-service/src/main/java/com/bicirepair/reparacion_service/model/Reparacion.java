package com.bicirepair.reparacion_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reparacion")
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReparacion;

    private Long idBicicleta;
    private Long idEmpleado;

    private String descripcionReparacion;
    private int costoTotal;
    private Date fechaReparacion;
    private String estadoReparacion;
}