package com.batuhankiltac.emlakburadaadvert.converter;

import com.batuhankiltac.emlakburadaadvert.domain.Advert;
import com.batuhankiltac.emlakburadaadvert.model.request.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.model.response.AdvertResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdvertConverter {
    private Long advertNo = 12344321L;

    public AdvertResponse convert(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .userId(advert.getUserId())
                .advertNo(advertNo)
                .advertType(advert.getAdvertType())
                .realEstateType(advert.getRealEstateType())
                .statusType(advert.getStatusType())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .province(advert.getProvince())
                .district(advert.getDistrict())
                .price(advert.getPrice())
                .rooms(advert.getRooms())
                .size(advert.getSize())
                .buildingAge(advert.getBuildingAge())
                .floor(advert.getFloor())
                .images(advert.getImages())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public Advert convert(AdvertRequest advertRequest) {
        return Advert.builder()
                .userId(advertRequest.getUserId())
                .advertNo(advertNo++)
                .advertType(advertRequest.getAdvertType())
                .realEstateType(advertRequest.getRealEstateType())
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .build();
    }
}