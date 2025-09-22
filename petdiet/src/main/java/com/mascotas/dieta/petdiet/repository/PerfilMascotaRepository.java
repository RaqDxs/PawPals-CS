package com.mascotas.dieta.petdiet.repository;

import com.mascotas.dieta.petdiet.model.PerfilMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerfilMascotaRepository extends JpaRepository<PerfilMascota, Long> {

    // 🔹 Buscar solo las mascotas de un usuario en específico
    List<PerfilMascota> findByUsuarioId(Long usuarioId);

}
