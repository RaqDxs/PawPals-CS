package com.mascotas.dieta.petdiet.mapper;

import com.mascotas.dieta.petdiet.dto.PerfilMascotaDTO;
import com.mascotas.dieta.petdiet.model.PerfilMascota;

public class PerfilMascotaMapper {

    // De entidad a DTO
    public static PerfilMascotaDTO toDTO(PerfilMascota perfil) {
        PerfilMascotaDTO dto = new PerfilMascotaDTO();
        // 🔹 Convertimos Long -> String
        dto.setId(perfil.getId() != null ? String.valueOf(perfil.getId()) : null);
        dto.setNombre(perfil.getNombre());
        dto.setEspecie(perfil.getEspecie());
        dto.setRaza(perfil.getRaza());
        dto.setEdad(perfil.getEdad());
        dto.setPeso(perfil.getPeso() != null ? perfil.getPeso().doubleValue() : null);
        dto.setFoto(perfil.getFoto());
        dto.setAlergias(perfil.getAlergias());
        dto.setCondiciones(perfil.getCondiciones());
        dto.setEstadoRegistro(perfil.getEstadoRegistro());
        dto.setUsuarioId(perfil.getUsuario() != null ? perfil.getUsuario().getId() : null);
        return dto;
    }

    // De DTO a entidad
    public static PerfilMascota toEntity(PerfilMascotaDTO dto) {
        PerfilMascota perfil = new PerfilMascota();
        // 🔹 Convertimos String -> Long
        if (dto.getId() != null) {
            perfil.setId(Long.parseLong(dto.getId()));
        }
        perfil.setNombre(dto.getNombre());
        perfil.setEspecie(dto.getEspecie());
        perfil.setRaza(dto.getRaza());
        perfil.setEdad(dto.getEdad());
        perfil.setPeso(dto.getPeso() != null ? java.math.BigDecimal.valueOf(dto.getPeso()) : null);
        perfil.setFoto(dto.getFoto());
        perfil.setAlergias(dto.getAlergias());
        perfil.setCondiciones(dto.getCondiciones());
        perfil.setEstadoRegistro(dto.getEstadoRegistro());
        return perfil;
    }
}
