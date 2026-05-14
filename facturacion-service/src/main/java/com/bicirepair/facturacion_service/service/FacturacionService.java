package com.bicirepair.facturacion_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.facturacion_service.model.Facturacion;
import com.bicirepair.facturacion_service.repository.FacturacionRepository;
import java.util.List;
import java.sql.Date;

@Service
public class FacturacionService {
    private final FacturacionRepository facturacionRepository;

    public FacturacionService(FacturacionRepository facturacionRepository){
        this.facturacionRepository = facturacionRepository;
    }
    public Facturacion guardar(Facturacion factura){
        return facturacionRepository.save(factura);    
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
