package com.batuhankiltac.emlakburadabanner.converter;

import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import org.springframework.stereotype.Component;

@Component
public class BannerConverter {
    private Long advertNo = 214324234L;

    public BannerResponse convert(Banner banner) {
        return BannerResponse.builder()
                .advertNo(banner.getAdvertNo())
                .title(banner.getTitle())
                .phone(banner.getPhone())
                .quantity(banner.getQuantity())
                .build();
    }

    public Banner convert(BannerRequest bannerRequest) {
        return Banner.builder()
                .advertNo(advertNo++)
                .title(bannerRequest.getTitle())
                .phone(bannerRequest.getPhone())
                .quantity(bannerRequest.getQuantity())
                .build();
    }
}