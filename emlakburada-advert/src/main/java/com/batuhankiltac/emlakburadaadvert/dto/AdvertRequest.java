package com.batuhankiltac.emlakburadaadvert.dto;

import com.batuhankiltac.emlakburadaadvert.model.enums.AdvertType;
import com.batuhankiltac.emlakburadaadvert.model.enums.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertRequest {

    private Long userId;
    private Long advertNo;
    private AdvertType advertType;
    private RealEstateType realEstateType;
    private String title;
    private String description;
}