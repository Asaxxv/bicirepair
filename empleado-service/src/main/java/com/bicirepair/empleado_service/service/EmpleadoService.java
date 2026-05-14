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

    public Empleado obtenerPorId(int idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElse(null);
    }

    public Empleado actualizar(int idEmpleado, Empleado empleadoNuevo) {
        return empleadoRepository.findById(idEmpleado).map(empleadoExistente -> {
            empleadoExistente.setRutEmp(empleadoNuevo.getRutEmp());
            empleadoExistente.setNombreEmp(empleadoNuevo.getNombreEmp());
            empleadoExistente.setCorreoEmp(empleadoNuevo.getCorreoEmp());
            empleadoExistente.setCargoEmp(empleadoNuevo.getCargoEmp());
            empleadoExistente.setTelefonoEmp(empleadoNuevo.getTelefonoEmp());
            return empleadoRepository.save(empleadoExistente);
        }).orElse(null);
    }

    public boolean eliminar(int idEmpleado) {
        if (!empleadoRepository.existsById(idEmpleado)) return false;
        empleadoRepository.deleteById(idEmpleado);
        return true;
    }

    public List<Empleado> buscarPorCargo(String cargoEmp) {
        return empleadoRepository.findByCargoEmp(cargoEmp);
    }

    public List<Empleado> buscarPorRut(String rutEmp) {
        return empleadoRepository.findByRutEmp(rutEmp);
    }

    public List<Empleado> buscarPorNombre(String nombreEmp) {
        return empleadoRepository.findByNombreEmp(nombreEmp);
    }
}
