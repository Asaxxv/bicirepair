package com.bicirepair.bicicleta_service.model;

import jakarta.persistence.*;    
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "bicicleta")
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBicicleta;
    @NotBlank(message = "La marca es obligatoria")
    @Column(nullable = false)
    private String marca;
    @NotBlank(message = "El modelo es obligatorio")
    @Column(nullable = false)
    private String modelo;
    @NotBlank(message = "El color es obligatorio")
    @Column(nullable = false)
    private String color;
    private Long idCliente; 
}