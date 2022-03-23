package com.batuhankiltac.emlakburadaadvert.dto;

import com.batuhankiltac.emlakburadaadvert.model.Image;
import com.batuhankiltac.emlakburadaadvert.model.enums.AdvertType;
import com.batuhankiltac.emlakburadaadvert.model.enums.RealEstateType;
import com.batuhankiltac.emlakburadaadvert.model.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Date createdDate;
}