package com.mascotas.dieta.petdiet.service;

import com.mascotas.dieta.petdiet.model.Usuario;
import com.mascotas.dieta.petdiet.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // 🔹 Register con encriptación
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuario) {
        return usuarioRepository.findById(id).map(u -> {
            u.setCorreo(usuario.getCorreo());
            // ⚠️ Si llega una nueva clave, encriptarla también
            u.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            u.setNombre(usuario.getNombre());
            u.setEstadoRegistro(usuario.getEstadoRegistro());
            return usuarioRepository.save(u);
        });
    }

    // 🔹 Login
    public boolean login(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            return false;
        }
        return passwordEncoder.matches(contrasena, usuario.getContrasena());
    }
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return Optional.ofNullable(usuarioRepository.findByCorreo(correo));
    }

}
