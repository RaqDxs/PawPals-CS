package com.mascotas.dieta.petdiet.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.PlanDieta;

public interface PlanDietaRepository extends JpaRepository<PlanDieta, Long> {
}
