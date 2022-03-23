package com.batuhankiltac.emlakburadaadvert.mapper;

import com.batuhankiltac.emlakburadaadvert.dto.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.model.Advert;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public class AdvertMapper {
    private Long advertNo = 12344321L;
    private final Date date = new Date();

    public AdvertResponse toDto(Advert advert) {
        AdvertResponse advertResponse = new AdvertResponse();
        advertResponse.setId(advert.getId());
        advertResponse.setUserId(advert.getUserId());
        advertResponse.setAdvertNo(advertNo);
        advertResponse.setAdvertType(advert.getAdvertType());
        advertResponse.setRealEstateType(advert.getRealEstateType());
        advertResponse.setStatusType(advert.getStatusType());
        advertResponse.setTitle(advert.getTitle());
        advertResponse.setDescription(advert.getDescription());
        advertResponse.setProvince(advert.getProvince());
        advertResponse.setDistrict(advert.getDistrict());
        advertResponse.setPrice(advert.getPrice());
        advertResponse.setRooms(advert.getRooms());
        advertResponse.setSize(advert.getSize());
        advertResponse.setBuildingAge(advert.getBuildingAge());
        advertResponse.setFloor(advert.getFloor());
        advertResponse.setImages(advert.getImages());
        advertResponse.setCreatedDate(date);
        return advertResponse;
    }
    public Advert toEntity(AdvertRequest advertRequest) {
        Advert advert = new Advert();
        advert.setUserId(advertRequest.getUserId());
        advert.setAdvertNo(advertNo++);
        advert.setAdvertType(advertRequest.getAdvertType());
        advert.setRealEstateType(advertRequest.getRealEstateType());
        advert.setTitle(advertRequest.getTitle());
        advert.setDescription(advertRequest.getDescription());
        return advert;
    }
}