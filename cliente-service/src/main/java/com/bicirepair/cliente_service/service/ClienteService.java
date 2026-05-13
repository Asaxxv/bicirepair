package com.bicirepair.cliente_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.cliente_service.model.Cliente;
import com.bicirepair.cliente_service.repository.ClienteRepository;
import java.util.List;

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
}
