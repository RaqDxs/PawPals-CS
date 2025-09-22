package com.mascotas.dieta.petdiet.controller;

import com.mascotas.dieta.petdiet.model.ItemCarrito;
import com.mascotas.dieta.petdiet.service.ItemCarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items-carrito")
public class ItemCarritoController {
    private final ItemCarritoService itemCarritoService;

    public ItemCarritoController(ItemCarritoService itemCarritoService) {
        this.itemCarritoService = itemCarritoService;
    }

    @GetMapping
    public List<ItemCarrito> listar() {
        return itemCarritoService.listarItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCarrito> buscar(@PathVariable Long id) {
        return itemCarritoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemCarrito guardar(@RequestBody ItemCarrito item) {
        return itemCarritoService.guardarItem(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        itemCarritoService.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ItemCarrito> actualizar(@PathVariable Long id, @RequestBody ItemCarrito item) {
        return itemCarritoService.actualizarItem(id, item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
