package com.bicirepair.categoria_service.controller;

import com.bicirepair.categoria_service.dto.CategoriaDTO;
import com.bicirepair.categoria_service.model.Categoria;
import com.bicirepair.categoria_service.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@RequestBody CategoriaDTO dto) {
        Categoria nueva = categoriaService.guardar(dto.toModel());
        return ResponseEntity.ok(CategoriaDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<CategoriaDTO> dtos = categoriaService.listar()
                .stream()
                .map(CategoriaDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable int id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CategoriaDTO.fromModel(categoria));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable int id) {
        return ResponseEntity.ok(categoriaService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable int id, @RequestBody CategoriaDTO dto) {
        Categoria actualizada = categoriaService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CategoriaDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!categoriaService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<CategoriaDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<CategoriaDTO> dtos = categoriaService.buscarPorNombre(nombre)
                .stream()
                .map(CategoriaDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}