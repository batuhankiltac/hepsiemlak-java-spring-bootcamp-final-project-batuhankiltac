package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.converter.ProductConverter;
import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.exception.GlobalException;
import com.batuhankiltac.emlakburadauser.model.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.model.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.model.response.UserResponse;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final UserService userService;

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Product not found."));
        return productConverter.convert(product);
    }

    public Set<Product> getPackagesByUserId(Long userId) {
        UserResponse userResponse = userService.getById(userId);
        return userResponse.getProducts();
    }

    public ProductResponse add(ProductRequest productRequest) {
        Product product = productConverter.convert(productRequest);
        return productConverter.convert(productRepository.save(product));
    }
}