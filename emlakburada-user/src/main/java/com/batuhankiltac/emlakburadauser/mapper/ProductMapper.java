package com.batuhankiltac.emlakburadauser.mapper;

import com.batuhankiltac.emlakburadauser.dto.request.ProductRequest;
import com.batuhankiltac.emlakburadauser.dto.response.ProductResponse;
import com.batuhankiltac.emlakburadauser.model.Product;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public class ProductMapper {
    Date date = new Date();
    Date expired = new Date(date.getTime() + (1000L * 60 * 60 * 24 * 30));
    Long orderNo = 123454320L;

    public Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setOrderNo(orderNo++);
        product.setCreatedDate(date);
        product.setExpiredDate(expired);
        return product;
    }

    public ProductResponse toDto(Product product) {
        ProductResponse productResponse = new ProductResponse();
        //productResponse.setPrice(product.getPrice());
        productResponse.setOrderNo(product.getOrderNo());
        productResponse.setName(product.getName());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCreatedDate(date);
        productResponse.setExpiredDate(expired);
        productResponse.setUsers(product.getUsers());
        return productResponse;
    }
}