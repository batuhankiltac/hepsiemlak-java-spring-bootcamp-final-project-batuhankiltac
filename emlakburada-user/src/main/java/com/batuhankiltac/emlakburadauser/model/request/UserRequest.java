package com.batuhankiltac.emlakburadauser.model.request;

import com.batuhankiltac.emlakburadauser.domain.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserRequest {
    private Long id;
    private UserType userType;
    private String name;
    private String email;
    private String password;
}