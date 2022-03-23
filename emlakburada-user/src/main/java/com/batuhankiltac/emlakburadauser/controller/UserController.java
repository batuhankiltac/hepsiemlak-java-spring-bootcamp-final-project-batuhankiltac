package com.batuhankiltac.emlakburadauser.controller;

import com.batuhankiltac.emlakburadauser.dto.request.UserRequest;
import com.batuhankiltac.emlakburadauser.dto.response.UserResponse;
import com.batuhankiltac.emlakburadauser.model.Product;
import com.batuhankiltac.emlakburadauser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/users/getIdIfQuantityExist/{userId}")
    public Object getIdIfQuantityExist(@PathVariable Long userId) {
        return userService.getIdIfQuantityExist(userId);
    }

    @GetMapping(value = "/users/products/{userId}")
    public ResponseEntity<List<Product>> getPackagesByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getPackagesByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.add(userRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.update(userRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/users/{userId}/products/{productId}")
    public ResponseEntity<UserResponse> updateQuantitiesAndDates(@PathVariable Long userId, @PathVariable Long productId) {
        return new ResponseEntity<>(userService.updateQuantitiesAndDates(userId, productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }
}