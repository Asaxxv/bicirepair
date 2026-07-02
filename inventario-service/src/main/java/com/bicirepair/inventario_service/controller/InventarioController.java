package com.bicirepair.inventario_service.controller;

import com.bicirepair.inventario_service.dto.InventarioDTO;
import com.bicirepair.inventario_service.model.Inventario;
import com.bicirepair.inventario_service.service.InventarioService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@Valid @RequestBody InventarioDTO dto) {
        Inventario nuevo = inventarioService.guardar(dto.toModel());
        return ResponseEntity.ok(InventarioDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<InventarioDTO>>> listar() {
        List<EntityModel<InventarioDTO>> inventarios = inventarioService.listar().stream()
                .map(InventarioDTO::fromModel)
                .map(dto -> {
                    EntityModel<InventarioDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(InventarioController.class).obtenerPorId(dto.getIdInventario())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioDTO>> obtenerPorId(@PathVariable Long id) {
        Inventario inventario = inventarioService.obtenerPorId(id);
        if (inventario == null) return ResponseEntity.notFound().build();

        InventarioDTO dto = InventarioDTO.fromModel(inventario);
        EntityModel<InventarioDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(InventarioController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(InventarioController.class).listar()).withRel("todos-los-inventarios"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Long id, @RequestBody InventarioDTO dto) {
        Inventario actualizado = inventarioService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(InventarioDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!inventarioService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<InventarioDTO>> buscarPorTipo(@PathVariable String tipo) {
        List<InventarioDTO> dtos = inventarioService.buscarPorTipo(tipo)
                .stream().map(InventarioDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<InventarioDTO>> buscarPorRangoFechas(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        List<InventarioDTO> dtos = inventarioService.buscarPorRangoFechas(inicio, fin)
                .stream().map(InventarioDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<InventarioDTO>> buscarPorProducto(@PathVariable Long idProducto) {
        List<InventarioDTO> dtos = inventarioService.buscarPorIdProducto(idProducto)
                .stream().map(InventarioDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}