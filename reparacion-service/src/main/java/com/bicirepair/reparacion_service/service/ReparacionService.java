package com.bicirepair.reparacion_service.service;

import org.springframework.stereotype.Service;
import com.bicirepair.reparacion_service.model.Reparacion;
import com.bicirepair.reparacion_service.model.DetalleReparacion;
import com.bicirepair.reparacion_service.repository.ReparacionRepository;
import com.bicirepair.reparacion_service.repository.DetalleReparacionRepository;
import java.sql.Date;
import java.util.List;

@Service
public class ReparacionService {

    private final ReparacionRepository reparacionRepository;
    private final DetalleReparacionRepository detalleReparacionRepository;

    public ReparacionService(ReparacionRepository reparacionRepository,
                             DetalleReparacionRepository detalleReparacionRepository) {
        this.reparacionRepository = reparacionRepository;
        this.detalleReparacionRepository = detalleReparacionRepository;
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

    public Reparacion obtenerPorId(int idReparacion) {
        return reparacionRepository.findById(idReparacion).orElse(null);
    }

    public Reparacion actualizar(int id, Reparacion reparacionNueva) {
        return reparacionRepository.findById(id).map(reparacionExistente -> {
            reparacionExistente.setIdBicicleta(reparacionNueva.getIdBicicleta());
            reparacionExistente.setIdEmpleado(reparacionNueva.getIdEmpleado());
            reparacionExistente.setDescripcionReparacion(reparacionNueva.getDescripcionReparacion());
            reparacionExistente.setCostoTotal(reparacionNueva.getCostoTotal());
            reparacionExistente.setFechaReparacion(reparacionNueva.getFechaReparacion());
            reparacionExistente.setEstadoReparacion(reparacionNueva.getEstadoReparacion());
            return reparacionRepository.save(reparacionExistente);
        }).orElse(null);
    }

    public boolean eliminar(int id) {
        if (!reparacionRepository.existsById(id)) return false;
        reparacionRepository.deleteById(id);
        return true;
    }

    public List<Reparacion> buscarPorEstado(String estado) {
        return reparacionRepository.findByEstadoReparacion(estado);
    }

    public List<Reparacion> buscarPorRangoFechas(Date inicio, Date fin) {
        return reparacionRepository.findByFechaReparacionBetween(inicio, fin);
    }

    public List<Reparacion> buscarPorIdEmpleado(int idEmpleado) {
        return reparacionRepository.findByIdEmpleado(idEmpleado);
    }

    // Detalle Reparacion
    public DetalleReparacion guardarDetalle(DetalleReparacion detalle) {
        return detalleReparacionRepository.save(detalle);
    }

    public List<DetalleReparacion> listarDetallesPorReparacion(int idReparacion) {
        return detalleReparacionRepository.findByIdReparacion(idReparacion);
    }
}