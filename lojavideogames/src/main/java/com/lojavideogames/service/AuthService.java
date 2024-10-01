package com.lojavideogames.service;

import com.lojavideogames.dtos.AuthenticationRequest;
import com.lojavideogames.dtos.AuthenticationResponse;

import com.lojavideogames.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Autentica o usuário
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Carrega os detalhes do usuário
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // Gera o token JWT
        String token = jwtUtil.generateToken(userDetails);

        // Retorna a resposta com o token
        return new AuthenticationResponse(token);
    }
}
