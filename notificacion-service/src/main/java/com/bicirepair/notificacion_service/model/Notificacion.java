package com.bicirepair.notificacion_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    private Long idReparacion;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
    @NotBlank(message = "El canal es obligatorio")
    private String canal;
    private boolean enviada;
    @NotBlank(message = "La fecha de envío es obligatoria")
    private Date fechaEnvio;
}