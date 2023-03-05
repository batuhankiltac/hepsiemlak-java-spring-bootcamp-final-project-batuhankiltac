package com.batuhankiltac.emlakburadaadvert.model.response;

import com.batuhankiltac.emlakburadaadvert.domain.Image;
import com.batuhankiltac.emlakburadaadvert.domain.enums.AdvertType;
import com.batuhankiltac.emlakburadaadvert.domain.enums.RealEstateType;
import com.batuhankiltac.emlakburadaadvert.domain.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertResponse {
    private Long id;
    private Long userId;
    private Long advertNo;
    private AdvertType advertType;
    private RealEstateType realEstateType;
    private StatusType statusType;
    private String title;
    private String description;
    private String province;
    private String district;
    private Long price;
    private String rooms;
    private Integer size;
    private Integer buildingAge;
    private Integer floor;
    private List<Image> images;
    private LocalDateTime createdDate;
}