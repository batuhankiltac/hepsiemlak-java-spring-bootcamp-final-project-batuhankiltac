package com.batuhankiltac.emlakburadauser.controller;

import com.batuhankiltac.emlakburadauser.dto.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.dto.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.add(productRequest), HttpStatus.CREATED);
    }
}