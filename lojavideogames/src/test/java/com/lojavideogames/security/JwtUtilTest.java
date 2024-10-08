package com.lojavideogames.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
public class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails userDetails;

    
    @BeforeEach
    public void setup() {
        jwtUtil = new JwtUtil();
        jwtUtil.generateSigningKey();
        userDetails = new User("TestUser", "TestPassword", new ArrayList<>());
    }

    @Test
    public void testGenerateToken() {
        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token); // Verifica se o token gerado não é nulo
    }

    @Test
    public void testExtractUsername() {
        String token = jwtUtil.generateToken(userDetails);
        String username = jwtUtil.extractUsername(token);
        assertEquals("TestUser", username); // Verifica se o username no token é o esperado
    }

    @Test
    public void testValidateToken() {
        String token = jwtUtil.generateToken(userDetails);
        boolean isValid = jwtUtil.validateToken(token, userDetails.getUsername());
        assertTrue(isValid); // Verifica se o token gerado é válido
    }
}

