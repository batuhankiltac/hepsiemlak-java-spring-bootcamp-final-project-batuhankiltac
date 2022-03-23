package com.batuhankiltac.emlakburadaauth.controller;

import com.batuhankiltac.emlakburadaauth.dto.AuthRequest;
import com.batuhankiltac.emlakburadaauth.dto.AuthResponse;
import com.batuhankiltac.emlakburadaauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(authService.getToken(authRequest), HttpStatus.OK);
    }
}