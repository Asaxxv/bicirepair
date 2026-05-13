package com.bicirepair.producto_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.producto_service.model.Producto;
import com.bicirepair.producto_service.repository.ProductoRepository;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardar(Producto prod) {
        return productoRepository.save(prod);
    }

    public boolean existeId(int idProducto) {
        return productoRepository.existsById(idProducto);
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }
}
