package com.batuhankiltac.emlakburadaauth.service;

import com.batuhankiltac.emlakburadaauth.model.AuthRequest;
import com.batuhankiltac.emlakburadaauth.model.AuthResponse;
import com.batuhankiltac.emlakburadaauth.exception.UserNotFoundException;
import com.batuhankiltac.emlakburadaauth.exception.UserPasswordNotValidException;
import com.batuhankiltac.emlakburadaauth.domain.User;
import com.batuhankiltac.emlakburadaauth.repository.AuthRepository;
import com.batuhankiltac.emlakburadaauth.util.JwtUtil;
import com.batuhankiltac.emlakburadaauth.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse getToken(AuthRequest authRequest) throws UserPasswordNotValidException {
        User user = authRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (!UserUtil.isValidPassword(user.getPassword(), authRequest.getPassword())) {
            log.error("User's password not valid " + authRequest.getEmail());
            throw new UserPasswordNotValidException("Password not valid!");
        }
        return new AuthResponse(jwtUtil.generateToken(user));
    }
}