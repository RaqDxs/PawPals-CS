package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.PerfilMascota;
import com.mascotas.dieta.petdiet.repository.PerfilMascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilMascotaService {
    private final PerfilMascotaRepository perfilMascotaRepository;

    public PerfilMascotaService(PerfilMascotaRepository perfilMascotaRepository) {
        this.perfilMascotaRepository = perfilMascotaRepository;
    }

    // 🔹 Listar todas (solo usar en admin)
    public List<PerfilMascota> listarPerfiles() {
        return perfilMascotaRepository.findAll();
    }

    // 🔹 Listar solo por dueño
    public List<PerfilMascota> listarPorUsuario(Long usuarioId) {
        return perfilMascotaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<PerfilMascota> buscarPorId(Long id) {
        return perfilMascotaRepository.findById(id);
    }

    public PerfilMascota guardarPerfil(PerfilMascota perfil) {
        return perfilMascotaRepository.save(perfil);
    }

    public void eliminarPerfil(Long id) {
        perfilMascotaRepository.deleteById(id);
    }

    public Optional<PerfilMascota> actualizarPerfil(Long id, PerfilMascota perfil) {
        return perfilMascotaRepository.findById(id).map(p -> {
            p.setUsuario(perfil.getUsuario()); // ⚠️ se revalida en el controller
            p.setNombre(perfil.getNombre());
            p.setEspecie(perfil.getEspecie());
            p.setRaza(perfil.getRaza());
            p.setEdad(perfil.getEdad());
            p.setPeso(perfil.getPeso());
            p.setFoto(perfil.getFoto());
            p.setAlergias(perfil.getAlergias());
            p.setCondiciones(perfil.getCondiciones());
            p.setEstadoRegistro(perfil.getEstadoRegistro());
            return perfilMascotaRepository.save(p);
        });
    }
}
