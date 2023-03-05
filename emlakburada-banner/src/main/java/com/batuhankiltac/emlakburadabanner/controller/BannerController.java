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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/banners")
public class BannerController {
    private final BannerService bannerService;

    @GetMapping
    public List<BannerResponse> getAll(){
        return bannerService.getAll();
    }

    @GetMapping(value = "/{id}")
    public BannerResponse getById(@PathVariable Long id) {
        return bannerService.getById(id);
    }

    @PostMapping
    public BannerResponse add(@RequestBody BannerRequest bannerRequest) {
        return bannerService.add(bannerRequest);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody BannerRequest bannerRequest, @PathVariable Long id) {
        bannerService.update(bannerRequest, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        bannerService.deleteById(id);
    }
}