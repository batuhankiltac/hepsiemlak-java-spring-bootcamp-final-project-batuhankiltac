package com.batuhankiltac.emlakburadauser.service;

import com.batuhankiltac.emlakburadauser.dto.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.dto.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.mapper.ProductMapper;
import com.batuhankiltac.emlakburadauser.model.Product;
import com.batuhankiltac.emlakburadauser.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse getById(Long id) {
        return productMapper.toDto(productRepository.getById(id));
    }

    public ProductResponse add(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        return productMapper.toDto(productRepository.save(product));
    }
}