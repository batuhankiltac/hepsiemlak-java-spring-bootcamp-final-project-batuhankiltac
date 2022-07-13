package com.batuhankiltac.emlakburadabanner.controller;

import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @GetMapping(value = "/banners")
    public List<BannerResponse> getAll(){
        return bannerService.getAll();
    }

    @GetMapping(value = "/banners/{id}")
    public BannerResponse getById(@PathVariable Long id) {
        return bannerService.getById(id);
    }

    @PostMapping(value = "/banners")
    public BannerResponse add(@RequestBody BannerRequest bannerRequest) {
        return bannerService.add(bannerRequest);
    }

    @PutMapping(value = "/banners/{id}")
    public BannerResponse update(@RequestBody BannerRequest bannerRequest, @PathVariable Long id) {
        return bannerService.update(bannerRequest, id);
    }

    @DeleteMapping(value = "/banners/{id}")
    public void deleteById(@PathVariable Long id) {
        bannerService.deleteById(id);
    }
}