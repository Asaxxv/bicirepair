package com.bicirepair.producto_service.controller;

import com.bicirepair.producto_service.dto.ProductoDTO;
import com.bicirepair.producto_service.model.Producto;
import com.bicirepair.producto_service.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        Producto nuevo = productoService.guardar(dto.toModel());
        return ResponseEntity.ok(ProductoDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<ProductoDTO> dtos = productoService.listar()
                .stream()
                .map(ProductoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable int id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProductoDTO.fromModel(producto));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable int id) {
        return ResponseEntity.ok(productoService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable int id, @RequestBody ProductoDTO dto) {
        Producto actualizado = productoService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProductoDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!productoService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProductoDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<ProductoDTO> dtos = productoService.buscarPorNombre(nombre)
                .stream()
                .map(ProductoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoDTO>> buscarPorCategoria(@PathVariable int idCategoria) {
        List<ProductoDTO> dtos = productoService.buscarPorIdCategoria(idCategoria)
                .stream()
                .map(ProductoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}