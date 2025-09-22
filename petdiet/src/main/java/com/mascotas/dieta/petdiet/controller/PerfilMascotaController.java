package com.mascotas.dieta.petdiet.controller;

import com.mascotas.dieta.petdiet.dto.PerfilMascotaDTO;
import com.mascotas.dieta.petdiet.mapper.PerfilMascotaMapper;
import com.mascotas.dieta.petdiet.model.PerfilMascota;
import com.mascotas.dieta.petdiet.model.Usuario;
import com.mascotas.dieta.petdiet.service.PerfilMascotaService;
import com.mascotas.dieta.petdiet.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilMascotaController {

    private final PerfilMascotaService perfilMascotaService;
    private final UsuarioService usuarioService;

    public PerfilMascotaController(PerfilMascotaService perfilMascotaService, UsuarioService usuarioService) {
        this.perfilMascotaService = perfilMascotaService;
        this.usuarioService = usuarioService;
    }

    // 🔹 Listar solo mascotas del usuario autenticado
    @GetMapping
    public List<PerfilMascotaDTO> listar(Principal principal) {
        String correo = principal.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return perfilMascotaService.listarPorUsuario(usuario.getId())
                .stream()
                .map(PerfilMascotaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Buscar mascota (recibimos el id como String para evitar problemas de precisión)
    @GetMapping("/{id}")
    public ResponseEntity<PerfilMascotaDTO> buscar(@PathVariable String id) {
        Long petId = Long.parseUnsignedLong(id); // convierte string → long seguro
        return perfilMascotaService.buscarPorId(petId)
                .map(m -> ResponseEntity.ok(PerfilMascotaMapper.toDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Crear mascota, siempre asociada al usuario autenticado
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody PerfilMascotaDTO dto, Principal principal) {
        try {
            String correo = principal.getName();
            Usuario usuario = usuarioService.buscarPorCorreo(correo)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            PerfilMascota entity = PerfilMascotaMapper.toEntity(dto);
            entity.setUsuario(usuario);

            PerfilMascota saved = perfilMascotaService.guardarPerfil(entity);
            return ResponseEntity.ok(PerfilMascotaMapper.toDTO(saved));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creando mascota: " + e.getMessage());
        }
    }

    // 🔹 Actualizar mascota, solo si es del usuario autenticado
    @PutMapping("/{id}")
    public ResponseEntity<PerfilMascotaDTO> actualizar(@PathVariable String id,
                                                       @RequestBody PerfilMascotaDTO dto,
                                                       Principal principal) {
        String correo = principal.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Long petId = Long.parseUnsignedLong(id);

        return perfilMascotaService.buscarPorId(petId)
                .filter(mascota -> mascota.getUsuario().getId().equals(usuario.getId()))
                .map(m -> {
                    PerfilMascota entity = PerfilMascotaMapper.toEntity(dto);
                    entity.setUsuario(usuario); // aseguramos dueño
                    return perfilMascotaService.actualizarPerfil(petId, entity)
                            .map(updated -> ResponseEntity.ok(PerfilMascotaMapper.toDTO(updated)))
                            .orElse(ResponseEntity.notFound().build());
                })
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    // 🔹 Eliminar mascota, solo si es del usuario autenticado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id, Principal principal) {
        String correo = principal.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Long petId = Long.parseUnsignedLong(id);

        return perfilMascotaService.buscarPorId(petId)
                .filter(mascota -> mascota.getUsuario().getId().equals(usuario.getId()))
                .map(m -> {
                    perfilMascotaService.eliminarPerfil(petId);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

}
    