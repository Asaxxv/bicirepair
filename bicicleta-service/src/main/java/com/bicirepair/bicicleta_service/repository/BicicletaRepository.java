package com.bicirepair.bicicleta_service.repository;

import com.bicirepair.bicicleta_service.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Integer> {
    List<Bicicleta> findByMarcaBicicleta(String marcaBicicleta);
    List<Bicicleta> findByModeloBicicleta(String modeloBicicleta);
    List<Bicicleta> findByIdCliente(int idCliente);;
}