package com.batuhankiltac.emlakburadaadvert.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerRequest {

    private Long advertNo;
    private String title;
    private String phone;
    private Integer quantity;
}