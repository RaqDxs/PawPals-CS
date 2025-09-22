package com.mascotas.dieta.petdiet.controller;

import com.mascotas.dieta.petdiet.model.PlanDieta;
import com.mascotas.dieta.petdiet.service.PlanDietaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlanDietaController {
    private final PlanDietaService planDietaService;

    public PlanDietaController(PlanDietaService planDietaService) {
        this.planDietaService = planDietaService;
    }

    @GetMapping
    public List<PlanDieta> listar() {
        return planDietaService.listarPlanes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDieta> buscar(@PathVariable Long id) {
        return planDietaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PlanDieta guardar(@RequestBody PlanDieta plan) {
        return planDietaService.guardarPlan(plan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        planDietaService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlanDieta> actualizar(@PathVariable Long id, @RequestBody PlanDieta plan) {
        return planDietaService.actualizarPlan(id, plan)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
