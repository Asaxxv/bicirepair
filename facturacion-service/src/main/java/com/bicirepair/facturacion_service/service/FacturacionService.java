package com.bicirepair.facturacion_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.facturacion_service.model.Facturacion;
import com.bicirepair.facturacion_service.repository.FacturacionRepository;
import java.util.List;

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
}
