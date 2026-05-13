package com.bicirepair.reparacion_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.reparacion_service.model.Reparacion;
import com.bicirepair.reparacion_service.repository.ReparacionRepository;
import java.util.List;

@Service
public class ReparacionService {
    private final ReparacionRepository reparacionRepository;

    public ReparacionService(ReparacionRepository reparacionRepository) {
        this.reparacionRepository = reparacionRepository;
    }

    public Reparacion guardar(Reparacion repair) {
        return reparacionRepository.save(repair);
    }

    public boolean existeId(int idReparacion) {
        return reparacionRepository.existsById(idReparacion);
    }

    public List<Reparacion> listar() {
        return reparacionRepository.findAll();
    }
}
