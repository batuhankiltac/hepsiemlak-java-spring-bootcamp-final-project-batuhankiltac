package com.batuhankiltac.emlakburadabanner.controller;

import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BannerController {
    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping(value = "/banners")
    public ResponseEntity<List<BannerResponse>> getAll(){
        return new ResponseEntity<>(bannerService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/banners/{id}")
    public ResponseEntity<BannerResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(bannerService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/banners")
    public ResponseEntity<BannerResponse> add(@RequestBody BannerRequest bannerRequest) {
        return new ResponseEntity<>(bannerService.add(bannerRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/banners/{id}")
    public ResponseEntity<BannerResponse> update(@RequestBody BannerRequest bannerRequest, @PathVariable Long id) {
        return new ResponseEntity<>(bannerService.update(bannerRequest, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/banners/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(bannerService.deleteById(id), HttpStatus.OK);
    }
}