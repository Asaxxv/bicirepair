package com.bicirepair.inventario_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    private Long idProducto;
    private Long idProveedor;
    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento;
    private int cantidad;
    @NotBlank(message = "La fecha del movimiento es obligatoria")
    private Date fechaMovimiento;
}