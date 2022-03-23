package com.batuhankiltac.emlakburadabanner.service;

import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.mapper.BannerMapper;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import com.batuhankiltac.emlakburadabanner.repository.BannerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BannerService {
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Autowired
    public BannerService(BannerRepository bannerRepository, BannerMapper bannerMapper) {
        this.bannerRepository = bannerRepository;
        this.bannerMapper = bannerMapper;
    }


    public List<BannerResponse> getAll() {
        List<BannerResponse> bannerList = new ArrayList<>();
        for (Banner banner : bannerRepository.findAll()) {
            bannerList.add(bannerMapper.toDto(banner));
        }
        log.info("Listed all banners.");
        return bannerList;
    }

    public BannerResponse getById(Long id) {
        return bannerMapper.toDto(bannerRepository.getById(id));
    }

    public BannerResponse add(BannerRequest bannerRequest) {
        Banner banner = bannerMapper.toEntity(bannerRequest);
        log.info("Banner has been created.");
        return bannerMapper.toDto(bannerRepository.save(banner));
    }

    public BannerResponse update(BannerRequest bannerRequest, Long id) {
        bannerRepository.getById(id);
        Banner banner = Banner.builder()
                .id(id)
                .advertNo(bannerRepository.getById(id).getAdvertNo())
                .title(bannerRequest.getTitle())
                .phone(bannerRequest.getPhone())
                .quantity(bannerRequest.getQuantity())
                .build();
        log.info("Banner has been updated.");
        return bannerMapper.toDto(bannerRepository.save(banner));
    }

    public ResponseEntity<String> deleteById(Long id) {
        getById(id);
        bannerRepository.deleteById(id);
        return ResponseEntity.ok("Banner has been deleted.");
    }
}