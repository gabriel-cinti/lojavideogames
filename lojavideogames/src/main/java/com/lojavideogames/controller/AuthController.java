package com.lojavideogames.controller;

import com.lojavideogames.dtos.AuthenticationRequest;
import com.lojavideogames.dtos.AuthenticationResponse;
import com.lojavideogames.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

