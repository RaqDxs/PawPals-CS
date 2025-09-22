package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.Pedido;
import com.mascotas.dieta.petdiet.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
    public Optional<Pedido> actualizarPedido(Long id, Pedido pedido) {
        return pedidoRepository.findById(id).map(p -> {
            p.setUsuario(pedido.getUsuario());
            p.setFecha(pedido.getFecha());
            p.setMonto(pedido.getMonto());
            p.setEstado(pedido.getEstado());
            p.setEstadoRegistro(pedido.getEstadoRegistro());
            return pedidoRepository.save(p);
        });
    }

}
