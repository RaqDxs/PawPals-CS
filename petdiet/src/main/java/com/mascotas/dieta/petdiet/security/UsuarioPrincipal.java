package com.mascotas.dieta.petdiet.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UsuarioPrincipal implements UserDetails {

    private Long id;
    private String correo;
    private String nombre;
    private String contrasena;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(Long id, String correo, String nombre, String contrasena,
                            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.authorities = authorities;
    }

    // 🔹 Constructor alternativo para usar en JwtFilter
    public UsuarioPrincipal(Long id, String correo, String nombre) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = null;
        this.authorities = new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getCorreo() { return correo; }
    public String getNombre() { return nombre; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo; // ⚡ Spring usará esto como principal.getName()
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
