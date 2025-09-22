package com.mascotas.dieta.petdiet.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // 🔹 Obtenemos todos los claims usando JwtUtil
                Claims claims = JwtUtil.getAllClaims(token);

                String correo = claims.getSubject();
                Long id = claims.get("id", Long.class);
                String nombre = claims.get("nombre", String.class);

                // ✅ No manejamos roles, solo datos básicos del usuario
                UsuarioPrincipal principal = new UsuarioPrincipal(
                        id,
                        correo,
                        nombre,
                        null,                   // contraseña no es necesaria en JWT
                        Collections.emptyList() // sin authorities
                );

                // Guardamos la autenticación en el contexto de seguridad
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                System.out.println("❌ Token inválido: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
