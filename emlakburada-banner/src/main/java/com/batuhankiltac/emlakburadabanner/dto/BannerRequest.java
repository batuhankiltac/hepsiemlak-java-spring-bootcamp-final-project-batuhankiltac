package com.batuhankiltac.emlakburadabanner.dto;

import lombok.Data;

@Data
public class BannerRequest {
    private String title;
    private String phone;
    private Integer quantity;
}