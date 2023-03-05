package com.batuhankiltac.emlakburadaadvert.model.request;

import com.batuhankiltac.emlakburadaadvert.domain.enums.AdvertType;
import com.batuhankiltac.emlakburadaadvert.domain.enums.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertRequest {
    private Long userId;
    private Long advertNo;
    private AdvertType advertType;
    private RealEstateType realEstateType;
    private String title;
    private String description;
}