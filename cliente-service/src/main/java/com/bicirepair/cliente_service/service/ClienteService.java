package com.bicirepair.cliente_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bicirepair.cliente_service.model.Cliente;
import com.bicirepair.cliente_service.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardar(Cliente cli) {
        return clienteRepository.save(cli);
    }

    public boolean existeId(int idCliente) {
        return clienteRepository.existsById(idCliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(int idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public Cliente actualizar(int id, Cliente clienteNuevo) {
        return clienteRepository.findById(id).map(clienteExistente -> {
            clienteExistente.setRutCliente(clienteNuevo.getRutCliente());
            clienteExistente.setNombreCliente(clienteNuevo.getNombreCliente());
            clienteExistente.setCorreoCliente(clienteNuevo.getCorreoCliente());
            clienteExistente.setTelefonoCliente(clienteNuevo.getTelefonoCliente());
            return clienteRepository.save(clienteExistente);
        }).orElse(null);
    }

    public boolean eliminar(int id) {
        if (!clienteRepository.existsById(id)) return false;
        clienteRepository.deleteById(id);
        return true;
    }

    public List<Cliente> buscarPorRut(String rut) {
        return clienteRepository.findByRutCliente(rut);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreCliente(nombre);
    }
}
