package com.bicirepair.empleado_service.service;

import org.springframework.stereotype.Service;

import com.bicirepair.empleado_service.model.Empleado;
import com.bicirepair.empleado_service.repository.EmpleadoRepository;
import java.util.List;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository){
        this.empleadoRepository = empleadoRepository;
    }
    public Empleado guardar(Empleado emp){
        return empleadoRepository.save(emp);    
    }

    public boolean existeId(int idEmpleado){
        return empleadoRepository.existsById(idEmpleado);
    }

    public List<Empleado> listar(){
        return empleadoRepository.findAll();
    }
}
