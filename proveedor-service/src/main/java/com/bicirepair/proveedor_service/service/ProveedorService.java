package com.bicirepair.proveedor_service.service;

import org.springframework.stereotype.Service;
import com.bicirepair.proveedor_service.model.Proveedor;
import com.bicirepair.proveedor_service.repository.ProveedorRepository;
import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor guardar(Proveedor prov) {
        return proveedorRepository.save(prov);
    }

    public boolean existeId(Long idProveedor) {
        return proveedorRepository.existsById(idProveedor);
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerPorId(Long idProveedor) {
        return proveedorRepository.findById(idProveedor).orElse(null);
    }

    public Proveedor actualizar(Long id, Proveedor proveedorNuevo) {
        return proveedorRepository.findById(id).map(proveedorExistente -> {
            proveedorExistente.setNombreProveedor(proveedorNuevo.getNombreProveedor());
            proveedorExistente.setRutProveedor(proveedorNuevo.getRutProveedor());
            proveedorExistente.setTelefonoProveedor(proveedorNuevo.getTelefonoProveedor());
            proveedorExistente.setCorreoProveedor(proveedorNuevo.getCorreoProveedor());
            proveedorExistente.setDireccionProveedor(proveedorNuevo.getDireccionProveedor());
            return proveedorRepository.save(proveedorExistente);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (!proveedorRepository.existsById(id)) return false;
        proveedorRepository.deleteById(id);
        return true;
    }

    public List<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNombreProveedor(nombre);
    }

    public List<Proveedor> buscarPorRut(String rut) {
        return proveedorRepository.findByRutProveedor(rut);
    }
}