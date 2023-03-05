package com.batuhankiltac.emlakburadauser.converter;

import com.batuhankiltac.emlakburadauser.domain.Product;
import com.batuhankiltac.emlakburadauser.model.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.model.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductConverter {
    Long orderNo = 123454320L;

    public ProductResponse convert(Product product) {
        return ProductResponse.builder()
                .orderNo(product.getOrderNo())
                .name(product.getName())
                .quantity(product.getQuantity())
                .createdDate(LocalDateTime.now())
                .expiredDate(LocalDateTime.now().plusDays(30))
                .users(product.getUsers())
                .build();
    }

    public Product convert(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .orderNo(orderNo++)
                .createdDate(LocalDateTime.now())
                .expiredDate(LocalDateTime.now().plusDays(30))
                .build();
    }
}