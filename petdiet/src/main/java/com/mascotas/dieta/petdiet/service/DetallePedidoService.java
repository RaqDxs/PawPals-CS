package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.DetallePedido;
import com.mascotas.dieta.petdiet.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<DetallePedido> listarDetalles() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> buscarPorId(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public DetallePedido guardarDetalle(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    public void eliminarDetalle(Long id) {
        detallePedidoRepository.deleteById(id);
    }
    public Optional<DetallePedido> actualizarDetalle(Long id, DetallePedido detalle) {
        return detallePedidoRepository.findById(id).map(d -> {
            d.setPedido(detalle.getPedido());
            d.setProducto(detalle.getProducto());
            d.setCantidad(detalle.getCantidad());
            d.setSubtotal(detalle.getSubtotal());
            d.setEstadoRegistro(detalle.getEstadoRegistro());
            return detallePedidoRepository.save(d);
        });
    }

}
