package com.bicirepair.producto_service.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreProducto;
    @NotBlank(message = "El precio es obligatorio")
    private int precio;
    @NotBlank(message = "La cantidad es obligatoria")
    private int cantidad;
    private Long idCategoria;
}