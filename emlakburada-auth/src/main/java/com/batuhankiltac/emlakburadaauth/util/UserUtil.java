package com.batuhankiltac.emlakburadaauth.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUtil {

    private UserUtil() {

    }

    public static boolean isValidPassword(String password, String password2) {
        return password.equals(password2);
    }
}