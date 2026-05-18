package com.bicirepair.empleado_service.controller;

import com.bicirepair.empleado_service.dto.EmpleadoDTO;
import com.bicirepair.empleado_service.model.Empleado;
import com.bicirepair.empleado_service.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crear(@RequestBody EmpleadoDTO dto) {
        Empleado nuevo = empleadoService.guardar(dto.toModel());
        return ResponseEntity.ok(EmpleadoDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        List<EmpleadoDTO> dtos = empleadoService.listar()
                .stream()
                .map(EmpleadoDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerPorId(@PathVariable int id) {
        Empleado empleado = empleadoService.obtenerPorId(id);
        if (empleado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmpleadoDTO.fromModel(empleado));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable int id) {
        return ResponseEntity.ok(empleadoService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable int id, @RequestBody EmpleadoDTO dto) {
        Empleado actualizado = empleadoService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(EmpleadoDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
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