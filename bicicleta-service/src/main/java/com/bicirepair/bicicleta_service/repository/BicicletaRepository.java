package com.bicirepair.bicicleta_service.repository;

import com.bicirepair.bicicleta_service.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {
    List<Bicicleta> findByMarca(String marca);
    List<Bicicleta> findByModelo(String modelo);
    List<Bicicleta> findByIdCliente(Long idCliente);
}