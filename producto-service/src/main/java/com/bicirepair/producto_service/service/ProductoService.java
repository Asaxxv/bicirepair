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

    public Producto obtenerPorId(int idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    public Producto actualizar(int id, Producto productoNuevo) {
        return productoRepository.findById(id).map(productoExistente -> {
            productoExistente.setNombreProducto(productoNuevo.getNombreProducto());
            productoExistente.setPrecio(productoNuevo.getPrecio());
            productoExistente.setCantidad(productoNuevo.getCantidad());
            productoExistente.setIdCategoria(productoNuevo.getIdCategoria());
            return productoRepository.save(productoExistente);
        }).orElse(null);
    }

    public boolean eliminar(int idProducto) {
        if (!productoRepository.existsById(idProducto)) return false;
        productoRepository.deleteById(idProducto);
        return true;
    }

    public List<Producto> buscarPorNombre(String nombreProducto) {
        return productoRepository.findByNombreProducto(nombreProducto);
    }

    public List<Producto> buscarPorIdCategoria(int idCategoria) {
        return productoRepository.findByIdCategoria(idCategoria);
    }
}
