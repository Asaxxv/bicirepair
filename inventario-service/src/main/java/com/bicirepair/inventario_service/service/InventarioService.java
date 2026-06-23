package com.bicirepair.inventario_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.bicirepair.inventario_service.model.Inventario;
import com.bicirepair.inventario_service.repository.InventarioRepository;
import java.time.LocalDate;
import java.util.List;

@Service
public class InventarioService {

    private static final Logger logger = LoggerFactory.getLogger(InventarioService.class);

    private final InventarioRepository inventarioRepository;
    private final WebClient productoWebClient;
    private final WebClient proveedorWebClient;

    @Value("${api.producto.exists}")
    private String productoExistsPath;

    @Value("${api.proveedor.exists}")
    private String proveedorExistsPath;

    public InventarioService(InventarioRepository inventarioRepository,
                             @Qualifier("productoWebClient") WebClient productoWebClient,
                             @Qualifier("proveedorWebClient") WebClient proveedorWebClient) {
        this.inventarioRepository = inventarioRepository;
        this.productoWebClient = productoWebClient;
        this.proveedorWebClient = proveedorWebClient;
    }

    public Inventario guardar(Inventario inv) {
        logger.info("Iniciando guardado de movimiento de inventario: idProducto={}, idProveedor={}",
                inv.getIdProducto(), inv.getIdProveedor());

        Boolean existeProducto;
        Boolean existeProveedor;

        try {
            existeProducto = productoWebClient.get()
                    .uri(String.format(productoExistsPath, inv.getIdProducto()))
                    .retrieve().bodyToMono(Boolean.class).block();
        } catch (Exception e) {
            logger.error("Error al validar producto id={}", inv.getIdProducto(), e);
            throw new RuntimeException("Error al validar producto");
        }

        try {
            existeProveedor = proveedorWebClient.get()
                    .uri(String.format(proveedorExistsPath, inv.getIdProveedor()))
                    .retrieve().bodyToMono(Boolean.class).block();
        } catch (Exception e) {
            logger.error("Error al validar proveedor id={}", inv.getIdProveedor(), e);
            throw new RuntimeException("Error al validar proveedor");
        }

        if (Boolean.FALSE.equals(existeProducto)) {
            logger.warn("Producto no existe id={}", inv.getIdProducto());
            throw new RuntimeException("El producto con id " + inv.getIdProducto() + " no existe");
        }

        if (Boolean.FALSE.equals(existeProveedor)) {
            logger.warn("Proveedor no existe id={}", inv.getIdProveedor());
            throw new RuntimeException("El proveedor con id " + inv.getIdProveedor() + " no existe");
        }

        Inventario guardado = inventarioRepository.save(inv);
        logger.info("Movimiento de inventario guardado con id={}", guardado.getIdInventario());
        return guardado;
    }

    public boolean existeId(Long idInventario) {
        return inventarioRepository.existsById(idInventario);
    }

    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    public Inventario obtenerPorId(Long idInventario) {
        return inventarioRepository.findById(idInventario).orElse(null);
    }

    public Inventario actualizar(Long id, Inventario invNuevo) {
        return inventarioRepository.findById(id).map(invExistente -> {
            invExistente.setIdProducto(invNuevo.getIdProducto());
            invExistente.setIdProveedor(invNuevo.getIdProveedor());
            invExistente.setTipoMovimiento(invNuevo.getTipoMovimiento());
            invExistente.setCantidad(invNuevo.getCantidad());
            invExistente.setFechaMovimiento(invNuevo.getFechaMovimiento());
            return inventarioRepository.save(invExistente);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (!inventarioRepository.existsById(id)) return false;
        inventarioRepository.deleteById(id);
        return true;
    }

    public List<Inventario> buscarPorTipo(String tipo) {
        return inventarioRepository.findByTipoMovimiento(tipo);
    }

    public List<Inventario> buscarPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return inventarioRepository.findByFechaMovimientoBetween(inicio, fin);
    }

    public List<Inventario> buscarPorIdProducto(Long idProducto) {
        return inventarioRepository.findByIdProducto(idProducto);
    }

    public List<Inventario> buscarPorIdProveedor(Long idProveedor) {
        return inventarioRepository.findByIdProveedor(idProveedor);
    }
}