package com.batuhankiltac.emlakburadauser.controller;

import com.batuhankiltac.emlakburadauser.model.request.UserRequest;
import com.batuhankiltac.emlakburadauser.model.response.UserResponse;
import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserResponse add(@RequestBody UserRequest userRequest) {
        return userService.add(userRequest);
    }

    @PutMapping
    public void update(@RequestBody UserRequest userRequest) {
        userService.update(userRequest);
    }

    @PutMapping(value = "/{userId}/products/{productId}")
    public UserResponse updateQuantitiesAndDates(@PathVariable Long userId, @PathVariable Long productId) {
        return userService.updateQuantitiesAndDates(userId, productId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}