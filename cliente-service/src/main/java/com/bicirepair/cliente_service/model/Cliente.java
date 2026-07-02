package com.bicirepair.cliente_service.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "El rut es obligatorio")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Formato de rut inválido")
    private String rutCliente;
    
    @Column(nullable = false)   
    private String correoCliente;
    
    @Column(nullable = false)
    private int telefonoCliente;
}