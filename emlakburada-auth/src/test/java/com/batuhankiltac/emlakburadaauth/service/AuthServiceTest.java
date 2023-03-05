package com.batuhankiltac.emlakburadaauth.service;

import com.batuhankiltac.emlakburadaauth.domain.User;
import com.batuhankiltac.emlakburadaauth.exception.UserPasswordNotValidException;
import com.batuhankiltac.emlakburadaauth.model.AuthRequest;
import com.batuhankiltac.emlakburadaauth.repository.AuthRepository;
import com.batuhankiltac.emlakburadaauth.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    void it_should_get_token_when_password_is_equal_to_each_other() {
        // Given
        AuthRequest authRequest = AuthRequest.builder()
                .email("test-mail")
                .password("test-password")
                .build();
        User user = User.builder()
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .build();
        when(authRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.of(user));

        // When
        authService.getToken(authRequest);

        // Then
        verify(authRepository).findByEmail(authRequest.getEmail());
        verify(jwtUtil).generateToken(user);
    }

    @Test
    void it_should_throw_exception_when_password_is_not_equal_to_each_other() {
        // Given
        AuthRequest authRequest = AuthRequest.builder()
                .email("test-mail")
                .password("test-password")
                .build();
        User user = User.builder()
                .email(authRequest.getEmail())
                .password("test-password2")
                .build();
        when(authRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.of(user));

        // When
        assertThatThrownBy(() -> authService.getToken(authRequest)).isInstanceOf(UserPasswordNotValidException.class);

        // Then
        verify(authRepository).findByEmail(authRequest.getEmail());
    }
}