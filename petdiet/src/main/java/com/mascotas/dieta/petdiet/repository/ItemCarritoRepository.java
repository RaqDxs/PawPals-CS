package com.mascotas.dieta.petdiet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
}
