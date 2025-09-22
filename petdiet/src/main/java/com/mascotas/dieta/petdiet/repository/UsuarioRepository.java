package com.mascotas.dieta.petdiet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mascotas.dieta.petdiet.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
