package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.converter.ProductConverter;
import com.batuhankiltac.emlakburadauser.dto.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.dto.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.model.Product;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductResponse getById(Long id) {
        return productConverter.toDto(productRepository.getById(id));
    }

    public ProductResponse add(ProductRequest productRequest) {
        Product product = productConverter.toEntity(productRequest);
        return productConverter.toDto(productRepository.save(product));
    }
}