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

    public boolean existeId(long idCategoria){
        return categoriaRepository.existsById(idCategoria);
    }

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(long idCategoria) {
        return categoriaRepository.findById(idCategoria).orElse(null);
    }

    public Categoria actualizar(long idCategoria, Categoria categoriaNueva) {
        return categoriaRepository.findById(idCategoria).map(categoriaExistente -> {
            categoriaExistente.setNombreCategoria(categoriaNueva.getNombreCategoria());
            return categoriaRepository.save(categoriaExistente);
        }).orElse(null);
    }

    public boolean eliminar(long idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) return false;
        categoriaRepository.deleteById(idCategoria);
        return true;
    }

    public List<Categoria> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombreCategoria(nombre);
    }
}
