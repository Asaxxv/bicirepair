package com.bicirepair.empleado_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;

    @NotBlank(message = "El rut es obligatorio")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Formato de rut inválido")
    private String rutEmp;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombreEmp;

    @NotBlank(message = "El correo es obligatorio")
    @Column(nullable = false)
    private String correoEmp;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Column(nullable = false)
    private int telefonoEmp;

    @NotBlank(message = "El cargo es obligatorio")
    @Column(nullable = false)
    private String cargoEmp;
 
    @Column(nullable = false)
    private Date fechaIngresoEmp;
}