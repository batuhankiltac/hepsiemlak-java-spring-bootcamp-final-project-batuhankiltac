package com.batuhankiltac.emlakburadabanner.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BannerRequest {
    private String title;
    private String phone;
    private Integer quantity;
}