package com.batuhankiltac.emlakburadauser.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequest {
    private Long id;
    private String name;
    private String creditCardNumber;
}