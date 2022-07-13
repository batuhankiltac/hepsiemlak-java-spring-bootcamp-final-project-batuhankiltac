package com.batuhankiltac.emlakburadauser.controller;

import com.batuhankiltac.emlakburadauser.dto.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.dto.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/products/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping(value = "/products")
    public ProductResponse add(@RequestBody ProductRequest productRequest) {
        return productService.add(productRequest);
    }
}