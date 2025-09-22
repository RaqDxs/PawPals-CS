package com.mascotas.dieta.petdiet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}
