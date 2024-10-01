package com.lojavideogames.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // A chave secreta deve ter no mínimo 256 bits (32 caracteres) para HMAC-SHA256
    private static final String SECRET_KEY = "secreta_secreta_secreta_secreta"; 

    // Gerar chave com base no SECRET_KEY
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Gerar o token JWT com base nos dados fornecidos
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Criar o token JWT
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token válido por 10 horas
                .signWith(getSigningKey())
                .compact();
    }

    // Extrair todos os claims do token JWT
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // Aqui está a chave
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrair o username do token JWT
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Validar o token JWT
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Verificar se o token JWT está expirado
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}

