package com.bicirepair.reparacion_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bicirepair.reparacion_service.model.Reparacion;
import org.springframework.web.reactive.function.client.WebClient;
import com.bicirepair.reparacion_service.model.DetalleReparacion;
import com.bicirepair.reparacion_service.repository.ReparacionRepository;
import com.bicirepair.reparacion_service.repository.DetalleReparacionRepository;
import java.sql.Date;
import java.util.List;

@Service
public class ReparacionService {
    private static final Logger logger = LoggerFactory.getLogger(ReparacionService.class);

    private final ReparacionRepository reparacionRepository;
    private final DetalleReparacionRepository detalleReparacionRepository;
    private final WebClient bicicletaWebClient;
    private final WebClient empleadoWebClient;

    @Value("${api.bicicleta.exists}")
    private String bicicletaExistsPath;

    @Value("${api.empleado.exists}")
    private String empleadoExistsPath;

    public ReparacionService(ReparacionRepository reparacionRepository,
                         DetalleReparacionRepository detalleReparacionRepository,
                         @Qualifier("bicicletaWebClient") WebClient bicicletaWebClient,
                         @Qualifier("empleadoWebClient") WebClient empleadoWebClient) {
    this.reparacionRepository = reparacionRepository;
    this.detalleReparacionRepository = detalleReparacionRepository;
    this.bicicletaWebClient = bicicletaWebClient;
    this.empleadoWebClient = empleadoWebClient;
}

    public Reparacion guardar(Reparacion repair) {
        logger.info("Iniciando guardado de reparacion: idBicicleta={}, idEmpleado={}",
                repair.getIdBicicleta(), repair.getIdEmpleado());

        Boolean existeBicicleta;
        Boolean existeEmpleado;

        try {
            logger.debug("Validando bicicleta id={}", repair.getIdBicicleta());
            existeBicicleta = bicicletaWebClient.get()
                    .uri(String.format(bicicletaExistsPath, repair.getIdBicicleta()))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error al validar bicicleta id={}", repair.getIdBicicleta(), e);
            throw new RuntimeException("Error al validar bicicleta");
        }

        try {
            logger.debug("Validando empleado id={}", repair.getIdEmpleado());
            existeEmpleado = empleadoWebClient.get()
                    .uri(String.format(empleadoExistsPath, repair.getIdEmpleado()))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error al validar empleado id={}", repair.getIdEmpleado(), e);
            throw new RuntimeException("Error al validar empleado");
        }

        if (Boolean.FALSE.equals(existeBicicleta)) {
            logger.warn("Bicicleta no existe id={}", repair.getIdBicicleta());
            throw new RuntimeException("La bicicleta con id " + repair.getIdBicicleta() + " no existe");
        }

        if (Boolean.FALSE.equals(existeEmpleado)) {
            logger.warn("Empleado no existe id={}", repair.getIdEmpleado());
            throw new RuntimeException("El empleado con id " + repair.getIdEmpleado() + " no existe");
        }

        Reparacion guardada = reparacionRepository.save(repair);
        logger.info("Reparacion guardada exitosamente con id={}", guardada.getIdReparacion());
        return guardada;
    }

    public boolean existeId(Long idReparacion) {
        return reparacionRepository.existsById(idReparacion);
    }

    public List<Reparacion> listar() {
        return reparacionRepository.findAll();
    }

    public Reparacion obtenerPorId(Long idReparacion) {
        return reparacionRepository.findById(idReparacion).orElse(null);
    }

    public Reparacion actualizar(Long id, Reparacion reparacionNueva) {
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

    public boolean eliminar(Long id) {
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

    public List<Reparacion> buscarPorIdEmpleado(Long idEmpleado) {
        return reparacionRepository.findByIdEmpleado(idEmpleado);
    }

    // Detalle Reparacion
    public DetalleReparacion guardarDetalle(DetalleReparacion detalle) {
        return detalleReparacionRepository.save(detalle);
    }

    public List<DetalleReparacion> listarDetallesPorReparacion(Long idReparacion) {
        return detalleReparacionRepository.findByIdReparacion(idReparacion);
    }
}