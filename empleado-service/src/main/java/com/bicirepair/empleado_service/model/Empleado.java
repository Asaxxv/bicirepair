package com.bicirepair.empleado_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @Column(nullable = false)
    private String rutEmp;
    
    @Column(nullable = false)
    private String nombreEmp;

    @Column(nullable = false)
    private String correoEmp;
    
    @Column(nullable = false)
    private int telefonoEmp;

    @Column(nullable = false)
    private String cargoEmp;

    @Column(nullable = false)
    private Date fechaIngresoEmp;
}