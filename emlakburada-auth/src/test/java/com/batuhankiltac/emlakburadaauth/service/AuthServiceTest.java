package com.batuhankiltac.emlakburadaauth.service;

import com.batuhankiltac.emlakburadaauth.dto.AuthRequest;
import com.batuhankiltac.emlakburadaauth.dto.AuthResponse;
import com.batuhankiltac.emlakburadaauth.model.User;
import com.batuhankiltac.emlakburadaauth.model.enums.UserType;
import com.batuhankiltac.emlakburadaauth.repository.AuthRepository;
import com.batuhankiltac.emlakburadaauth.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void init() {

        Mockito
                .when(authRepository.findByEmail("test-email"))
                .thenReturn(Optional.of(prepareUser()));
    }

    @Test
    void getToken() {
        AuthResponse authResponse = authService.getToken(prepareRequest());
        assertEquals(jwtToken(), authResponse.getToken());
    }

    private AuthRequest prepareRequest() {
        return new AuthRequest("test-email", "test-password");
    }

    private User prepareUser() {
        User user = new User();
        user.setId(1L);
        user.setUserType(UserType.INDIVIDUAL);
        user.setEmail("test-email");
        user.setPassword("test-password");
        return user;
    }

    private String jwtToken() {
        return jwtUtil.generateToken(prepareUser());
    }
}