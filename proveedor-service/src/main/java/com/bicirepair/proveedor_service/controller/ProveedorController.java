package com.bicirepair.proveedor_service.controller;

import com.bicirepair.proveedor_service.dto.ProveedorDTO;
import com.bicirepair.proveedor_service.model.Proveedor;
import com.bicirepair.proveedor_service.service.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@Valid @RequestBody ProveedorDTO dto) {
        Proveedor nuevo = proveedorService.guardar(dto.toModel());
        return ResponseEntity.ok(ProveedorDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ProveedorDTO>>> listar() {
        List<EntityModel<ProveedorDTO>> proveedores = proveedorService.listar().stream()
                .map(ProveedorDTO::fromModel)
                .map(dto -> {
                    EntityModel<ProveedorDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(ProveedorController.class).obtenerPorId(dto.getIdProveedor())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> obtenerPorId(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        if (proveedor == null) return ResponseEntity.notFound().build();

        ProveedorDTO dto = ProveedorDTO.fromModel(proveedor);
        EntityModel<ProveedorDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(ProveedorController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ProveedorController.class).listar()).withRel("todos-los-proveedores"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(@PathVariable Long id, @RequestBody ProveedorDTO dto) {
        Proveedor actualizado = proveedorService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProveedorDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!proveedorService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProveedorDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<ProveedorDTO> dtos = proveedorService.buscarPorNombre(nombre)
                .stream().map(ProveedorDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<List<ProveedorDTO>> buscarPorRut(@PathVariable String rut) {
        List<ProveedorDTO> dtos = proveedorService.buscarPorRut(rut)
                .stream().map(ProveedorDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}