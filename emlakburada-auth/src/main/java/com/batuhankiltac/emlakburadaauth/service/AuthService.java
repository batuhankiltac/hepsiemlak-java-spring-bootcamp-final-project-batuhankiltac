package com.batuhankiltac.emlakburadaauth.service;

import com.batuhankiltac.emlakburadaauth.dto.AuthRequest;
import com.batuhankiltac.emlakburadaauth.dto.AuthResponse;
import com.batuhankiltac.emlakburadaauth.exception.UserNotFoundException;
import com.batuhankiltac.emlakburadaauth.exception.UserPasswordNotValidException;
import com.batuhankiltac.emlakburadaauth.model.User;
import com.batuhankiltac.emlakburadaauth.repository.AuthRepository;
import com.batuhankiltac.emlakburadaauth.util.JwtUtil;
import com.batuhankiltac.emlakburadaauth.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthRepository authRepository, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse getToken(AuthRequest request) throws UserPasswordNotValidException {
        User user = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));


        if (!UserUtil.isValidPassword(user.getPassword(), request.getPassword())) {
            log.error("User's password not valid " + request.getEmail());
            throw new UserPasswordNotValidException("Password not valid!");
        }

        return new AuthResponse(jwtUtil.generateToken(user));
    }
}