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

    public boolean existeId(int idBicicleta){
        return bicicletaRepository.existsById(idBicicleta);
    }

    public List<Bicicleta> listar(){
        return bicicletaRepository.findAll();
    }
}
