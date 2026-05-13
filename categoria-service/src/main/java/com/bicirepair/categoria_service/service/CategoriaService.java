package com.bicirepair.categoria_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.categoria_service.model.Categoria;
import com.bicirepair.categoria_service.repository.CategoriaRepository;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }
    public Categoria guardar(Categoria categ){
        return categoriaRepository.save(categ);    
    }

    public boolean existeId(int idCategoria){
        return categoriaRepository.existsById(idCategoria);
    }

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }
}
