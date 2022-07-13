package com.batuhankiltac.emlakburadauser.dto.request;

import com.batuhankiltac.emlakburadauser.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequest {

    private Long id;
    private UserType userType;
    private String name;
    private String email;
    private String password;
}