package com.bicirepair.producto_service.controller;

import com.bicirepair.producto_service.dto.ProductoDTO;
import com.bicirepair.producto_service.model.Producto;
import com.bicirepair.producto_service.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO dto) {
        Producto nuevo = productoService.guardar(dto.toModel());
        return ResponseEntity.ok(ProductoDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ProductoDTO>>> listar() {
        List<EntityModel<ProductoDTO>> productos = productoService.listar().stream()
                .map(ProductoDTO::fromModel)
                .map(dto -> {
                    EntityModel<ProductoDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(ProductoController.class).obtenerPorId(dto.getIdProducto())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductoDTO>> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) return ResponseEntity.notFound().build();

        ProductoDTO dto = ProductoDTO.fromModel(producto);
        EntityModel<ProductoDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(ProductoController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ProductoController.class).listar()).withRel("todos-los-productos"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        Producto actualizado = productoService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ProductoDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
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
    public ResponseEntity<List<ProductoDTO>> buscarPorCategoria(@PathVariable Long idCategoria) {
        List<ProductoDTO> dtos = productoService.buscarPorIdCategoria(idCategoria)
                .stream()
                .map(ProductoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}