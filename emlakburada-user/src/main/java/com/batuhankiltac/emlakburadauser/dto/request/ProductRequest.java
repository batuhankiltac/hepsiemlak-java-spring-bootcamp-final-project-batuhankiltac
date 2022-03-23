package com.batuhankiltac.emlakburadauser.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private Long id;
    private String name;
    private String creditCardNumber;
}