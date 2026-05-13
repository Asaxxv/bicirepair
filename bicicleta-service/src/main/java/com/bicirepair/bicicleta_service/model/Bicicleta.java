package com.bicirepair.bicicleta_service.model;

import jakarta.persistence.*;    
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "bicicleta")
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBicicleta;
    
    private String marca;
    private String modelo;
    private String color;
    private int idCliente; 
}