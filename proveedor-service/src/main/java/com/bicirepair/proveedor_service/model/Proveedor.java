package com.bicirepair.proveedor_service.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreProveedor;
    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "Formato de rut inválido")
    private String rutProveedor;
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefonoProveedor;
    @NotBlank(message = "El correo es obligatorio")
    private String correoProveedor;
    private String direccionProveedor;
}