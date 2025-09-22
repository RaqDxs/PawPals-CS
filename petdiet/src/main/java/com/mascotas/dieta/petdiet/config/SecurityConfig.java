package com.mascotas.dieta.petdiet.config;

import com.mascotas.dieta.petdiet.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())   // ❌ Desactiva CSRF
            .cors(cors -> {})               // ✅ Usa tu CorsConfig
            .authorizeHttpRequests(auth -> auth
                // 👉 Endpoints públicos
                .requestMatchers(
                    "/api/usuarios/login",
                    "/api/usuarios/register",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()

                // 👉 Hacemos público SOLO el GET por id de mascota
                .requestMatchers(HttpMethod.GET, "/api/perfiles/**").permitAll()

                // 👉 El resto requiere autenticación
                .anyRequest().authenticated()
            )
            // 👉 Filtro JWT antes del de autenticación básica
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
