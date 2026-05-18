package com.bicirepair.bicicleta_service.controller;

import com.bicirepair.bicicleta_service.dto.BicicletaDTO;
import com.bicirepair.bicicleta_service.model.Bicicleta;
import com.bicirepair.bicicleta_service.service.BicicletaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bicicletas")
public class BicicletaController {

    private final BicicletaService bicicletaService;

    public BicicletaController(BicicletaService bicicletaService) {
        this.bicicletaService = bicicletaService;
    }

    @PostMapping
    public ResponseEntity<BicicletaDTO> crear(@RequestBody BicicletaDTO dto) {
        Bicicleta nueva = bicicletaService.guardar(dto.toModel());
        return ResponseEntity.ok(BicicletaDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<BicicletaDTO>> listar() {
        List<BicicletaDTO> dtos = bicicletaService.listar()
                .stream()
                .map(BicicletaDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BicicletaDTO> obtenerPorId(@PathVariable int id) {
        Bicicleta bicicleta = bicicletaService.obtenerPorId(id);
        if (bicicleta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(BicicletaDTO.fromModel(bicicleta));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable int id) {
        return ResponseEntity.ok(bicicletaService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BicicletaDTO> actualizar(@PathVariable int id, @RequestBody BicicletaDTO dto) {
        Bicicleta actualizada = bicicletaService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(BicicletaDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!bicicletaService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<BicicletaDTO>> buscarPorMarca(@PathVariable String marca) {
        List<BicicletaDTO> dtos = bicicletaService.buscarPorMarca(marca)
                .stream()
                .map(BicicletaDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<BicicletaDTO>> buscarPorModelo(@PathVariable String modelo) {
        List<BicicletaDTO> dtos = bicicletaService.buscarPorModelo(modelo)
                .stream()
                .map(BicicletaDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<BicicletaDTO>> buscarPorIdCliente(@PathVariable int idCliente) {
        List<BicicletaDTO> dtos = bicicletaService.buscarPorIdCliente(idCliente)
                .stream()
                .map(BicicletaDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}