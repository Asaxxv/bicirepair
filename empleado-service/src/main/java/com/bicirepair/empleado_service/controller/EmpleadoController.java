package com.bicirepair.empleado_service.controller;

import com.bicirepair.empleado_service.dto.EmpleadoDTO;
import com.bicirepair.empleado_service.model.Empleado;
import com.bicirepair.empleado_service.service.EmpleadoService;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crear(@Valid @RequestBody EmpleadoDTO dto) {
        Empleado nuevo = empleadoService.guardar(dto.toModel());
        return ResponseEntity.ok(EmpleadoDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<EmpleadoDTO>>> listar() {
        List<EntityModel<EmpleadoDTO>> empleados = empleadoService.listar().stream()
                .map(EmpleadoDTO::fromModel)
                .map(dto -> {
                    EntityModel<EmpleadoDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(EmpleadoController.class).obtenerPorId(dto.getIdEmpleado())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmpleadoDTO>> obtenerPorId(@PathVariable long id) {
        Empleado empleado = empleadoService.obtenerPorId(id);
        if (empleado == null) return ResponseEntity.notFound().build();

        EmpleadoDTO dto = EmpleadoDTO.fromModel(empleado);
        EntityModel<EmpleadoDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(EmpleadoController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(EmpleadoController.class).listar()).withRel("todos-los-empleados"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable long id) {
        return ResponseEntity.ok(empleadoService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable long id, @RequestBody EmpleadoDTO dto) {
        Empleado actualizado = empleadoService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmpleadoDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        if (!empleadoService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<EmpleadoDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<EmpleadoDTO> dtos = empleadoService.buscarPorNombre(nombre)
                .stream()
                .map(EmpleadoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<EmpleadoDTO>> buscarPorCargo(@PathVariable String cargo) {
        List<EmpleadoDTO> dtos = empleadoService.buscarPorCargo(cargo)
                .stream()
                .map(EmpleadoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<List<EmpleadoDTO>> buscarPorRut(@PathVariable String rut) {
        List<EmpleadoDTO> dtos = empleadoService.buscarPorRut(rut)
                .stream()
                .map(EmpleadoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}