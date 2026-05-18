package com.bicirepair.facturacion_service.service;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import com.bicirepair.facturacion_service.model.Facturacion;
import com.bicirepair.facturacion_service.repository.FacturacionRepository;
import java.util.List;
import java.sql.Date;

@Service
public class FacturacionService {
    
    private static final Logger logger = LoggerFactory.getLogger(FacturacionService.class);
    
    private final FacturacionRepository facturacionRepository;
    private final WebClient reparacionWebClient;

     @Value("${api.reparacion.exists}")
    private String reparacionExistsPath;

    public FacturacionService(FacturacionRepository facturacionRepository,
                              WebClient reparacionWebClient) {
        this.facturacionRepository = facturacionRepository;
        this.reparacionWebClient = reparacionWebClient;
    }
    
    public Facturacion guardar(Facturacion factura) {
        logger.info("Iniciando guardado de factura: idReparacion={}", factura.getIdReparacion());

        Boolean existeReparacion;

        try {
            logger.debug("Validando reparacion id={}", factura.getIdReparacion());
            existeReparacion = reparacionWebClient.get()
                    .uri(String.format(reparacionExistsPath, factura.getIdReparacion()))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error al validar reparacion id={}", factura.getIdReparacion(), e);
            throw new RuntimeException("Error al validar reparacion");
        }

        if (Boolean.FALSE.equals(existeReparacion)) {
            logger.warn("Reparacion no existe id={}", factura.getIdReparacion());
            throw new RuntimeException("La reparacion con id " + factura.getIdReparacion() + " no existe");
        }

        Facturacion guardada = facturacionRepository.save(factura);
        logger.info("Factura guardada exitosamente con id={}", guardada.getIdFactura());
        return guardada;
    }

    public boolean existeId(int idFactura){
        return facturacionRepository.existsById(idFactura);
    }

    public List<Facturacion> listar(){
        return facturacionRepository.findAll();
    }

    public Facturacion obtenerPorId(int idFactura) {
        return facturacionRepository.findById(idFactura).orElse(null);
    }

    public Facturacion actualizar(int id, Facturacion facturaNueva) {
        return facturacionRepository.findById(id).map(facturaExistente -> {
            facturaExistente.setIdReparacion(facturaNueva.getIdReparacion());
            facturaExistente.setCobroTotal(facturaNueva.getCobroTotal());
            facturaExistente.setMetodoPago(facturaNueva.getMetodoPago());
            facturaExistente.setFechaFactura(facturaNueva.getFechaFactura());
            return facturacionRepository.save(facturaExistente);
        }).orElse(null);
    }

    public boolean eliminar(int idFactura) {
        if (!facturacionRepository.existsById(idFactura)) return false;
        facturacionRepository.deleteById(idFactura);
        return true;
    }

    public List<Facturacion> buscarPorMetodoPago(String metodoPago) {
        return facturacionRepository.findByMetodoPago(metodoPago);
    }

    public List<Facturacion> buscarPorRangoFechas(Date inicio, Date fin) {
        return facturacionRepository.findByFechaFacturaBetween(inicio, fin);
    }

    public List<Facturacion> buscarPorIdReparacion(int idReparacion) {
        return facturacionRepository.findByIdReparacion(idReparacion);
    }
}
