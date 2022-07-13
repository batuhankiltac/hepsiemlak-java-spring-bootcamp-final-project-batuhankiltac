package com.batuhankiltac.emlakburadauser.controller;

import com.batuhankiltac.emlakburadauser.dto.request.UserRequest;
import com.batuhankiltac.emlakburadauser.dto.response.UserResponse;
import com.batuhankiltac.emlakburadauser.model.Product;
import com.batuhankiltac.emlakburadauser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/users")
    public List<UserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/users/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "/users/getIdIfQuantityExist/{userId}")
    public Object getIdIfQuantityExist(@PathVariable Long userId) {
        return userService.getIdIfQuantityExist(userId);
    }

    @GetMapping(value = "/users/packages/{userId}")
    public List<Product> getPackagesByUserId(@PathVariable Long userId) {
        return userService.getPackagesByUserId(userId);
    }

    @PostMapping(value = "/users")
    public UserResponse add(@RequestBody UserRequest userRequest) {
        return userService.add(userRequest);
    }

    @PutMapping(value = "/users")
    public UserResponse update(@RequestBody UserRequest userRequest) {
        return userService.update(userRequest);
    }

    @PutMapping(value = "/users/{userId}/products/{productId}")
    public UserResponse updateQuantitiesAndDates(@PathVariable Long userId, @PathVariable Long productId) {
        return userService.updateQuantitiesAndDates(userId, productId);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}