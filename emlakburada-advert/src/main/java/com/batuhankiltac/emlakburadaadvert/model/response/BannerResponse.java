package com.batuhankiltac.emlakburadaadvert.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponse {

    private Long advertNo;
    private String title;
    private String phone;
    private Integer quantity;
}