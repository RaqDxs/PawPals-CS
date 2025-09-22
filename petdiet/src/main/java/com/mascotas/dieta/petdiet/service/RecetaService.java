package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.Receta;
import com.mascotas.dieta.petdiet.repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> buscarPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public Receta guardarReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }
    public Optional<Receta> actualizarReceta(Long id, Receta receta) {
        return recetaRepository.findById(id).map(r -> {
            r.setUsuario(receta.getUsuario());
            r.setArchivo(receta.getArchivo());
            r.setFecha(receta.getFecha());
            r.setTipo(receta.getTipo());
            r.setEstadoRegistro(receta.getEstadoRegistro());
            return recetaRepository.save(r);
        });
    }

}
