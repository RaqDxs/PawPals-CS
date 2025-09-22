package com.mascotas.dieta.petdiet.controller;

import com.mascotas.dieta.petdiet.model.Receta;
import com.mascotas.dieta.petdiet.service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public List<Receta> listar() {
        return recetaService.listarRecetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> buscar(@PathVariable Long id) {
        return recetaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Receta guardar(@RequestBody Receta receta) {
        return recetaService.guardarReceta(receta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizar(@PathVariable Long id, @RequestBody Receta receta) {
        return recetaService.actualizarReceta(id, receta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
