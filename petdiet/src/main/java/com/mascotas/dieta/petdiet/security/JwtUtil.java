package com.mascotas.dieta.petdiet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import com.mascotas.dieta.petdiet.model.Usuario;

public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora
    private static final PrivateKey privateKey = loadPrivateKey("src/main/resources/keys/private.pem");
    private static final PublicKey publicKey = loadPublicKey("src/main/resources/keys/public.pem");

    // 🔹 Generar token con claims extra
    public static String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreo()) // claim estándar
                .claim("id", usuario.getId())     // claim custom
                .claim("nombre", usuario.getNombre()) // claim custom
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    // 🔹 Obtener solo el correo (sub)
    public static String getCorreoFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 🔹 Obtener todos los claims
    public static Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // --- Helpers para leer claves PEM ---
    private static PrivateKey loadPrivateKey(String filename) {
        try {
            String key = new String(Files.readAllBytes(Paths.get(filename)))
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando clave privada", e);
        }
    }

    private static PublicKey loadPublicKey(String filename) {
        try {
            String key = new String(Files.readAllBytes(Paths.get(filename)))
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            return KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando clave pública", e);
        }
    }
}
