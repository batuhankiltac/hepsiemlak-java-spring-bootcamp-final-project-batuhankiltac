package com.batuhankiltac.emlakburadaadvert.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponse {
    private Long advertNo;
    private String title;
    private String phone;
    private Integer quantity;
}