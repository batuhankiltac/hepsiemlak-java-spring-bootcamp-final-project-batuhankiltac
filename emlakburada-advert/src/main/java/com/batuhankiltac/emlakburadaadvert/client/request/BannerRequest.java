package com.batuhankiltac.emlakburadaadvert.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerRequest {
    private Long advertNo;
    private String title;
    private String phone;
    private Integer quantity;
}