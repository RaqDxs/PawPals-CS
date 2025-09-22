package com.mascotas.dieta.petdiet.controller;

import com.mascotas.dieta.petdiet.model.DetallePedido;
import com.mascotas.dieta.petdiet.service.DetallePedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedido> listar() {
        return detallePedidoService.listarDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscar(@PathVariable Long id) {
        return detallePedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetallePedido guardar(@RequestBody DetallePedido detalle) {
        return detallePedidoService.guardarDetalle(detalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detallePedidoService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizar(@PathVariable Long id, @RequestBody DetallePedido detalle) {
        return detallePedidoService.actualizarDetalle(id, detalle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
