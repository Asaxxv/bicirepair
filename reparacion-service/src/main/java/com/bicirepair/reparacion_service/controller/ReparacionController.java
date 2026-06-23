package com.bicirepair.reparacion_service.controller;

import com.bicirepair.reparacion_service.dto.DetalleReparacionDTO;
import com.bicirepair.reparacion_service.dto.ReparacionDTO;
import com.bicirepair.reparacion_service.model.DetalleReparacion;
import com.bicirepair.reparacion_service.model.Reparacion;
import com.bicirepair.reparacion_service.service.ReparacionService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/reparaciones")
public class ReparacionController {

    private final ReparacionService reparacionService;

    public ReparacionController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

    @PostMapping
    public ResponseEntity<ReparacionDTO> crear(@Valid @RequestBody ReparacionDTO dto) {
        Reparacion nueva = reparacionService.guardar(dto.toModel());
        return ResponseEntity.ok(ReparacionDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ReparacionDTO>>> listar() {
        List<EntityModel<ReparacionDTO>> reparaciones = reparacionService.listar().stream()
                .map(ReparacionDTO::fromModel)
                .map(dto -> {
                    EntityModel<ReparacionDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(ReparacionController.class).obtenerPorId(dto.getIdReparacion())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(reparaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReparacionDTO>> obtenerPorId(@PathVariable Long id) {
        Reparacion reparacion = reparacionService.obtenerPorId(id);
        if (reparacion == null) return ResponseEntity.notFound().build();

        ReparacionDTO dto = ReparacionDTO.fromModel(reparacion);
        EntityModel<ReparacionDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(ReparacionController.class).obtenerPorId(dto.getIdReparacion())).withSelfRel());
        recurso.add(linkTo(methodOn(ReparacionController.class).listar()).withRel("todas-las-reparaciones"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(reparacionService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReparacionDTO> actualizar(@PathVariable Long id, @RequestBody ReparacionDTO dto) {
        Reparacion actualizada = reparacionService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ReparacionDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!reparacionService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReparacionDTO>> buscarPorEstado(@PathVariable String estado) {
        List<ReparacionDTO> dtos = reparacionService.buscarPorEstado(estado)
                .stream()
                .map(ReparacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<ReparacionDTO>> buscarPorRangoFechas(
            @RequestParam Date inicio,
            @RequestParam Date fin) {
        List<ReparacionDTO> dtos = reparacionService.buscarPorRangoFechas(inicio, fin)
                .stream()
                .map(ReparacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<ReparacionDTO>> buscarPorEmpleado(@PathVariable Long idEmpleado) {
        List<ReparacionDTO> dtos = reparacionService.buscarPorIdEmpleado(idEmpleado)
                .stream()
                .map(ReparacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Detalle Reparacion
    @PostMapping("/detalle")
    public ResponseEntity<DetalleReparacionDTO> crearDetalle(@RequestBody DetalleReparacionDTO dto) {
        DetalleReparacion nuevo = reparacionService.guardarDetalle(dto.toModel());
        return ResponseEntity.ok(DetalleReparacionDTO.fromModel(nuevo));
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetalleReparacionDTO>> listarDetalles(@PathVariable Long id) {
        List<DetalleReparacionDTO> dtos = reparacionService.listarDetallesPorReparacion(id)
                .stream()
                .map(DetalleReparacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}