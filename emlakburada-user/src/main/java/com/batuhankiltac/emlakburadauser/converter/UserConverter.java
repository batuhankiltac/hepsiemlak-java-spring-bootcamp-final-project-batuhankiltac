package com.batuhankiltac.emlakburadauser.converter;

import com.batuhankiltac.emlakburadauser.dto.request.UserRequest;
import com.batuhankiltac.emlakburadauser.dto.response.UserResponse;
import com.batuhankiltac.emlakburadauser.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UserConverter {

    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserType(user.getUserType());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setProducts(user.getProducts());
        userResponse.setQuantity(user.getUserQuantity());
        userResponse.setStartedDate(user.getPackageStartedDate());
        userResponse.setExpiredDate(user.getPackageExpiredDate());
        return userResponse;
    }

    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setId(userRequest.getId());
        user.setUserType(userRequest.getUserType());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

}