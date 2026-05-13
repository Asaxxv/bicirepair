package com.bicirepair.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    
    @Column(unique = true, nullable = false)
    private String rutCliente;
    
    @Column(nullable = false)
    private String nombreCliente;
    
    @Column(nullable = false)   
    private String correoCliente;
    
    @Column(nullable = false)
    private int telefonoCliente;
}