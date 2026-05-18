package com.bicirepair.facturacion_service.controller;

import com.bicirepair.facturacion_service.dto.FacturacionDTO;
import com.bicirepair.facturacion_service.model.Facturacion;
import com.bicirepair.facturacion_service.service.FacturacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/facturas")
public class FacturacionController {

    private final FacturacionService facturacionService;

    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    @PostMapping
    public ResponseEntity<FacturacionDTO> crear(@RequestBody FacturacionDTO dto) {
        Facturacion nueva = facturacionService.guardar(dto.toModel());
        return ResponseEntity.ok(FacturacionDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<FacturacionDTO>> listar() {
        List<FacturacionDTO> dtos = facturacionService.listar()
                .stream()
                .map(FacturacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturacionDTO> obtenerPorId(@PathVariable int id) {
        Facturacion factura = facturacionService.obtenerPorId(id);
        if (factura == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(FacturacionDTO.fromModel(factura));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable int id) {
        return ResponseEntity.ok(facturacionService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturacionDTO> actualizar(@PathVariable int id, @RequestBody FacturacionDTO dto) {
        Facturacion actualizada = facturacionService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(FacturacionDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
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
    public ResponseEntity<List<FacturacionDTO>> buscarPorReparacion(@PathVariable int idReparacion) {
        List<FacturacionDTO> dtos = facturacionService.buscarPorIdReparacion(idReparacion)
                .stream()
                .map(FacturacionDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}