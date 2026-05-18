package com.bicirepair.cliente_service.controller;

import com.bicirepair.cliente_service.dto.ClienteDTO;
import com.bicirepair.cliente_service.model.Cliente;
import com.bicirepair.cliente_service.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto) {
        Cliente nuevo = clienteService.guardar(dto.toModel());
        return ResponseEntity.ok(ClienteDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> dtos = clienteService.listar()
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable int id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ClienteDTO.fromModel(cliente));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable int id) {
        return ResponseEntity.ok(clienteService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable int id, @RequestBody ClienteDTO dto) {
        Cliente actualizado = clienteService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ClienteDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (!clienteService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<List<ClienteDTO>> buscarPorRut(@PathVariable String rut) {
        List<ClienteDTO> dtos = clienteService.buscarPorRut(rut)
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ClienteDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<ClienteDTO> dtos = clienteService.buscarPorNombre(nombre)
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}