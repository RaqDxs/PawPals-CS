package com.mascotas.dieta.petdiet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
