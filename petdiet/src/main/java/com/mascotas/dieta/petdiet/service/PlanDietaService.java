package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.PlanDieta;
import com.mascotas.dieta.petdiet.repository.PlanDietaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanDietaService {
    private final PlanDietaRepository planDietaRepository;

    public PlanDietaService(PlanDietaRepository planDietaRepository) {
        this.planDietaRepository = planDietaRepository;
    }

    public List<PlanDieta> listarPlanes() {
        return planDietaRepository.findAll();
    }

    public Optional<PlanDieta> buscarPorId(Long id) {
        return planDietaRepository.findById(id);
    }

    public PlanDieta guardarPlan(PlanDieta plan) {
        return planDietaRepository.save(plan);
    }

    public void eliminarPlan(Long id) {
        planDietaRepository.deleteById(id);
    }
    public Optional<PlanDieta> actualizarPlan(Long id, PlanDieta plan) {
        return planDietaRepository.findById(id).map(p -> {
            p.setNombre(plan.getNombre());
            p.setIngredientes(plan.getIngredientes());
            p.setFoto(plan.getFoto());
            p.setDescripcion(plan.getDescripcion());
            p.setEtiquetas(plan.getEtiquetas());
            p.setEstadoRegistro(plan.getEstadoRegistro());
            return planDietaRepository.save(p);
        });
    }

}
