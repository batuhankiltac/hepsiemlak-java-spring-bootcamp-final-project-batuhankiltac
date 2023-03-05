package com.batuhankiltac.emlakburadaadvert.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerRequest {
    private Long advertNo;
    private String title;
    private String phone;
    private Integer quantity;
}