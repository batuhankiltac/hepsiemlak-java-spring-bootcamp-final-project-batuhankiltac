package com.batuhankiltac.emlakburadaadvert.client;

import com.batuhankiltac.emlakburadaadvert.model.request.BannerRequest;
import com.batuhankiltac.emlakburadaadvert.model.response.BannerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banner-feign", url = "http://localhost:8082")
public interface BannerClient {

    @PostMapping(value = "/banners")
    BannerResponse addBanner(@RequestBody BannerRequest bannerRequest);
}