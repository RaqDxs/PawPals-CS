package com.mascotas.dieta.petdiet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
