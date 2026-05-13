package com.bicirepair.categoria_service.repository;

import com.bicirepair.categoria_service.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByNombreCategoria(String nombreCategoria);
}