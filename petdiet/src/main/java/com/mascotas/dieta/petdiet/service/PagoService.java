package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.Pago;
import com.mascotas.dieta.petdiet.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
    public Optional<Pago> actualizarPago(Long id, Pago pago) {
        return pagoRepository.findById(id).map(p -> {
            p.setPedido(pago.getPedido());
            p.setMonto(pago.getMonto());
            p.setMetodo(pago.getMetodo());
            p.setComprobante(pago.getComprobante());
            p.setFecha(pago.getFecha());
            p.setEstadoRegistro(pago.getEstadoRegistro());
            return pagoRepository.save(p);
        });
    }

}
