package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.ItemCarrito;
import com.mascotas.dieta.petdiet.repository.ItemCarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCarritoService {
    private final ItemCarritoRepository itemCarritoRepository;

    public ItemCarritoService(ItemCarritoRepository itemCarritoRepository) {
        this.itemCarritoRepository = itemCarritoRepository;
    }

    public List<ItemCarrito> listarItems() {
        return itemCarritoRepository.findAll();
    }

    public Optional<ItemCarrito> buscarPorId(Long id) {
        return itemCarritoRepository.findById(id);
    }

    public ItemCarrito guardarItem(ItemCarrito item) {
        return itemCarritoRepository.save(item);
    }

    public void eliminarItem(Long id) {
        itemCarritoRepository.deleteById(id);
    }
    public Optional<ItemCarrito> actualizarItem(Long id, ItemCarrito item) {
        return itemCarritoRepository.findById(id).map(i -> {
            i.setCarrito(item.getCarrito());
            i.setProducto(item.getProducto());
            i.setCantidad(item.getCantidad());
            i.setEstadoRegistro(item.getEstadoRegistro());
            return itemCarritoRepository.save(i);
        });
    }

}
