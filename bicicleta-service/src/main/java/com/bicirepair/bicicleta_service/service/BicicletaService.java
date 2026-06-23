package com.bicirepair.bicicleta_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.bicicleta_service.model.Bicicleta;
import com.bicirepair.bicicleta_service.repository.BicicletaRepository;
import java.util.List;

@Service
public class BicicletaService {
    private final BicicletaRepository bicicletaRepository;

    public BicicletaService(BicicletaRepository bicicletaRepository){
        this.bicicletaRepository = bicicletaRepository;
    }
    public Bicicleta guardar(Bicicleta bicicleta){
        return bicicletaRepository.save(bicicleta);    
    }

    public boolean existeId(Long idBicicleta){
        return bicicletaRepository.existsById(idBicicleta);
    }

    public List<Bicicleta> listar(){
        return bicicletaRepository.findAll();
    }

    public Bicicleta obtenerPorId(Long idBicicleta) {
        return bicicletaRepository.findById(idBicicleta).orElse(null);
    }

    public Bicicleta actualizar(Long idBicicleta, Bicicleta bicicletaNueva) {
        return bicicletaRepository.findById(idBicicleta).map(bicicletaExistente -> {
            bicicletaExistente.setMarca(bicicletaNueva.getMarca());
            bicicletaExistente.setModelo(bicicletaNueva.getModelo());
            return bicicletaRepository.save(bicicletaExistente);
        }).orElse(null);
    }

    public boolean eliminar(Long idBicicleta) {
        if (!bicicletaRepository.existsById(idBicicleta)) return false;
        bicicletaRepository.deleteById(idBicicleta);
        return true;
    }

    public List<Bicicleta> buscarPorModelo(String modelo) {
        return bicicletaRepository.findByModelo(modelo);
    }

    public List<Bicicleta> buscarPorMarca(String marca) {
        return bicicletaRepository.findByMarca(marca);
    }

    public List<Bicicleta> buscarPorIdCliente(Long idCliente) {
        return bicicletaRepository.findByIdCliente(idCliente);
    }
}
