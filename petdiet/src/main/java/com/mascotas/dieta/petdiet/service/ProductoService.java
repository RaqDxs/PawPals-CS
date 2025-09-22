package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.Producto;
import com.mascotas.dieta.petdiet.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(producto.getNombre());
            p.setDescripcion(producto.getDescripcion());
            p.setCategoria(producto.getCategoria());
            p.setPrecio(producto.getPrecio());
            p.setFoto(producto.getFoto());
            p.setEtiquetas(producto.getEtiquetas());
            p.setEstadoRegistro(producto.getEstadoRegistro());
            return productoRepository.save(p);
        });
    }

}
