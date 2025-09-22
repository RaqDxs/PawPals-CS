package com.mascotas.dieta.petdiet.controller;

import com.mascotas.dieta.petdiet.model.Usuario;
import com.mascotas.dieta.petdiet.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mascotas.dieta.petdiet.dto.UsuarioDTO;
import com.mascotas.dieta.petdiet.security.JwtUtil;
import com.mascotas.dieta.petdiet.dto.AuthResponse;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Register (ya encripta la clave en UsuarioService)
    @PostMapping("/register")
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Usuario usuario) {
    boolean autenticado = usuarioService.login(usuario.getCorreo(), usuario.getContrasena());

    if (autenticado) {
        Usuario usuarioDB = usuarioService.buscarPorCorreo(usuario.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = JwtUtil.generateToken(usuarioDB);

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuarioDB.getId(),
                usuarioDB.getCorreo(),
                usuarioDB.getNombre()
        );

        return ResponseEntity.ok(new AuthResponse(token, usuarioDTO));
    } else {
        return ResponseEntity.status(401).body("Credenciales inválidas ❌");
    }
}
}
