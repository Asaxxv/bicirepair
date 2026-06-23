package com.bicirepair.reparacion_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import jakarta.validation.constraints.NotBlank;

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
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcionReparacion;
    @NotBlank(message = "El total es obligatorio")
    private int costoTotal;
    @NotBlank(message = "La fecha es obligatoria")
    private Date fechaReparacion;
    @NotBlank(message = "El estado de reparación es obligatorio")
    private String estadoReparacion;
}