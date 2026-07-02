package com.bicirepair.categoria_service.controller;

import com.bicirepair.categoria_service.dto.CategoriaDTO;
import com.bicirepair.categoria_service.model.Categoria;
import com.bicirepair.categoria_service.service.CategoriaService;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody CategoriaDTO dto) {
        Categoria nueva = categoriaService.guardar(dto.toModel());
        return ResponseEntity.ok(CategoriaDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CategoriaDTO>>> listar() {
        List<EntityModel<CategoriaDTO>> categorias = categoriaService.listar().stream()
                .map(CategoriaDTO::fromModel)
                .map(dto -> {
                    EntityModel<CategoriaDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(CategoriaController.class).obtenerPorId(dto.getIdCategoria())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoriaDTO>> obtenerPorId(@PathVariable long id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null) return ResponseEntity.notFound().build();

        CategoriaDTO dto = CategoriaDTO.fromModel(categoria);
        EntityModel<CategoriaDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(CategoriaController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(CategoriaController.class).listar()).withRel("todas-las-categorias"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable long id) {
        return ResponseEntity.ok(categoriaService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable long id, @RequestBody CategoriaDTO dto) {
        Categoria actualizada = categoriaService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(CategoriaDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
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