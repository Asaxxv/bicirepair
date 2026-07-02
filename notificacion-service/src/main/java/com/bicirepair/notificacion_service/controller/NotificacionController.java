package com.bicirepair.notificacion_service.controller;

import com.bicirepair.notificacion_service.dto.NotificacionDTO;
import com.bicirepair.notificacion_service.model.Notificacion;
import com.bicirepair.notificacion_service.service.NotificacionService;

import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public ResponseEntity<NotificacionDTO> crear(@Valid @RequestBody NotificacionDTO dto) {
        Notificacion nueva = notificacionService.guardar(dto.toModel());
        return ResponseEntity.ok(NotificacionDTO.fromModel(nueva));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<NotificacionDTO>>> listar() {
        List<EntityModel<NotificacionDTO>> notificaciones = notificacionService.listar().stream()
                .map(NotificacionDTO::fromModel)
                .map(dto -> {
                    EntityModel<NotificacionDTO> recurso = EntityModel.of(dto);
                    recurso.add(linkTo(methodOn(NotificacionController.class).obtenerPorId(dto.getIdNotificacion())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<NotificacionDTO>> obtenerPorId(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.obtenerPorId(id);
        if (notificacion == null) return ResponseEntity.notFound().build();

        NotificacionDTO dto = NotificacionDTO.fromModel(notificacion);
        EntityModel<NotificacionDTO> recurso = EntityModel.of(dto);
        recurso.add(linkTo(methodOn(NotificacionController.class).obtenerPorId(dto.getIdNotificacion())).withSelfRel());
        recurso.add(linkTo(methodOn(NotificacionController.class).listar()).withRel("todas-las-notificaciones"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.existeId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionDTO> actualizar(@PathVariable Long id, @RequestBody NotificacionDTO dto) {
        Notificacion actualizada = notificacionService.actualizar(id, dto.toModel());
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(NotificacionDTO.fromModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!notificacionService.eliminar(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/canal/{canal}")
    public ResponseEntity<List<NotificacionDTO>> buscarPorCanal(@PathVariable String canal) {
        List<NotificacionDTO> dtos = notificacionService.buscarPorCanal(canal)
                .stream().map(NotificacionDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<NotificacionDTO>> buscarPorRangoFechas(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        List<NotificacionDTO> dtos = notificacionService.buscarPorRangoFechas(inicio, fin)
                .stream().map(NotificacionDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/reparacion/{idReparacion}")
    public ResponseEntity<List<NotificacionDTO>> buscarPorReparacion(@PathVariable Long idReparacion) {
        List<NotificacionDTO> dtos = notificacionService.buscarPorIdReparacion(idReparacion)
                .stream().map(NotificacionDTO::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}