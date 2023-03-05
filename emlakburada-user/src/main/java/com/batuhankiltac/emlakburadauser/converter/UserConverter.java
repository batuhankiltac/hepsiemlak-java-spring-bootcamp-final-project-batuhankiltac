package com.batuhankiltac.emlakburadauser.converter;

import com.batuhankiltac.emlakburadauser.domain.User;
import com.batuhankiltac.emlakburadauser.model.request.UserRequest;
import com.batuhankiltac.emlakburadauser.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userType(user.getUserType())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .products(user.getProducts())
                .quantity(user.getUserQuantity())
                .startedDate(user.getPackageStartedDate())
                .expiredDate(user.getPackageExpiredDate())
                .build();
    }

    public User convert(UserRequest userRequest) {
        return User.builder()
                .id(userRequest.getId())
                .userType(userRequest.getUserType())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}