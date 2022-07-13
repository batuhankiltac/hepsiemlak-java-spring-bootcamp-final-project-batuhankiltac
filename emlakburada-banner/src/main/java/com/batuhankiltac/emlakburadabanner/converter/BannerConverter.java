package com.batuhankiltac.emlakburadabanner.converter;

import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

@Slf4j
@Mapper(componentModel = "spring")
public class BannerConverter {
    private Long advertNo = 214324234L;

    public BannerResponse toDto(Banner banner) {
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setAdvertNo(banner.getAdvertNo());
        bannerResponse.setTitle(banner.getTitle());
        bannerResponse.setPhone(banner.getPhone());
        bannerResponse.setQuantity(banner.getQuantity());
        return bannerResponse;
    }

    public Banner toEntity(BannerRequest bannerRequest) {
        Banner banner = new Banner();
        banner.setAdvertNo(advertNo++);
        banner.setTitle(bannerRequest.getTitle());
        banner.setPhone(bannerRequest.getPhone());
        banner.setQuantity(bannerRequest.getQuantity());
        return banner;
    }
}