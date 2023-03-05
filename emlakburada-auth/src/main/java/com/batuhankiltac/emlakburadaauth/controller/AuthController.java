package com.batuhankiltac.emlakburadaauth.controller;

import com.batuhankiltac.emlakburadaauth.model.AuthRequest;
import com.batuhankiltac.emlakburadaauth.model.AuthResponse;
import com.batuhankiltac.emlakburadaauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping
    public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
        return authService.getToken(authRequest);
    }
}