package com.batuhankiltac.emlakburadauser.controller;

import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.model.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.model.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping(value = "/packages/{userId}")
    public Set<Product> getPackagesByUserId(@PathVariable Long userId) {
        return productService.getPackagesByUserId(userId);
    }

    @PostMapping
    public ProductResponse add(@RequestBody ProductRequest productRequest) {
        return productService.add(productRequest);
    }
}