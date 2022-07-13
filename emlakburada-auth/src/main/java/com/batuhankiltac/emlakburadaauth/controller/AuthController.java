package com.batuhankiltac.emlakburadaauth.controller;

import com.batuhankiltac.emlakburadaauth.dto.AuthRequest;
import com.batuhankiltac.emlakburadaauth.dto.AuthResponse;
import com.batuhankiltac.emlakburadaauth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/auth")
    public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
        return authService.getToken(authRequest);
    }
}