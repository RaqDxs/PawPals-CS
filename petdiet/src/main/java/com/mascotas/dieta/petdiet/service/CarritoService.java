package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.Carrito;
import com.mascotas.dieta.petdiet.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {
    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> buscarPorId(Long id) {
        return carritoRepository.findById(id);
    }

    public Carrito guardarCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }
    public Optional<Carrito> actualizarCarrito(Long id, Carrito carrito) {
        return carritoRepository.findById(id).map(c -> {
            c.setUsuario(carrito.getUsuario());
            c.setFecha(carrito.getFecha());
            c.setEstadoRegistro(carrito.getEstadoRegistro());
            return carritoRepository.save(c);
        });
    }

}
