package com.bicirepair.facturacion_service.controller;

import com.bicirepair.facturacion_service.dto.FacturacionDTO;
import com.bicirepair.facturacion_service.model.Facturacion;
import com.bicirepair.facturacion_service.service.FacturacionService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/facturas")
public class FacturacionController {

    private final FacturacionService facturacionService;

    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    @PostMapping
    public ResponseEntity<FacturacionDTO> crear(@Valid @RequestBody FacturacionDTO dto) {
        Facturacion nueva = facturacionService.guardar(dto.toModel());
        return ResponseEntity.ok(FacturacionDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<FacturacionDTO>>> listar() {
        List<EntityModel<FacturacionDTO>> facturas = facturacionService.listar().stream()
                .map(FacturacionDTO::fromModel)
                .map(dto -> {
                    EntityModel<FacturacionDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(FacturacionController.class).obtenerPorId(dto.getIdFactura())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<FacturacionDTO>> obtenerPorId(@PathVariable Long id) {
        Facturacion facturacion = facturacionService.obtenerPorId(id);
        if (facturacion == null) return ResponseEntity.notFound().build();

        FacturacionDTO dto = FacturacionDTO.fromModel(facturacion);
        EntityModel<FacturacionDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(FacturacionController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(FacturacionController.class).listar()).withRel("todas-las-facturas"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(facturacionService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturacionDTO> actualizar(@PathVariable Long id, @RequestBody FacturacionDTO dto) {
        Facturacion actualizada = facturacionService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(FacturacionDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!facturacionService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/metodo/{metodoPago}")
    public ResponseEntity<List<FacturacionDTO>> buscarPorMetodoPago(@PathVariable String metodoPago) {
        List<FacturacionDTO> dtos = facturacionService.buscarPorMetodoPago(metodoPago)
                .stream()
                .map(FacturacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<FacturacionDTO>> buscarPorRangoFechas(
            @RequestParam Date inicio,
            @RequestParam Date fin) {
        List<FacturacionDTO> dtos = facturacionService.buscarPorRangoFechas(inicio, fin)
                .stream()
                .map(FacturacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/reparacion/{idReparacion}")
    public ResponseEntity<List<FacturacionDTO>> buscarPorReparacion(@PathVariable Long idReparacion) {
        List<FacturacionDTO> dtos = facturacionService.buscarPorIdReparacion(idReparacion)
                .stream()
                .map(FacturacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}