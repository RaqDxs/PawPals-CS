package com.mascotas.dieta.petdiet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
