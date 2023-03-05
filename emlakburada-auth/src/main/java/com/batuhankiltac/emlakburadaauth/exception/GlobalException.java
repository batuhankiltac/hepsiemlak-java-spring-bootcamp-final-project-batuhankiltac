package com.batuhankiltac.emlakburadaauth.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final String message;

    public GlobalException(String message) {
        this.message = message;
    }
}