package com.bicirepair.cliente_service.controller;

import com.bicirepair.cliente_service.dto.ClienteDTO;
import com.bicirepair.cliente_service.model.Cliente;
import com.bicirepair.cliente_service.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO dto) {
        Cliente nuevo = clienteService.guardar(dto.toModel());
        return ResponseEntity.ok(ClienteDTO.fromModel(nuevo));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<ClienteDTO>>> listar() {
        List<EntityModel<ClienteDTO>> clientes = clienteService.listar().stream()
                .map(ClienteDTO::fromModel)
                .map(dto -> {
                    EntityModel<ClienteDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(ClienteController.class).obtenerPorId(dto.getIdCliente())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteDTO>> obtenerPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        if (cliente == null) return ResponseEntity.notFound().build();

        ClienteDTO dto = ClienteDTO.fromModel(cliente);
        EntityModel<ClienteDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(ClienteController.class).obtenerPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ClienteController.class).listar()).withRel("todos-los-clientes"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        Cliente actualizado = clienteService.actualizar(id, dto.toModel());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ClienteDTO.fromModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
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