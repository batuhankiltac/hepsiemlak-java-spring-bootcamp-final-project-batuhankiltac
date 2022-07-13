package com.batuhankiltac.emlakburadabanner.service;

import com.batuhankiltac.emlakburadabanner.converter.BannerConverter;
import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import com.batuhankiltac.emlakburadabanner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;
    private final BannerConverter bannerConverter;

    public List<BannerResponse> getAll() {
        List<BannerResponse> bannerList = new ArrayList<>();
        for (Banner banner : bannerRepository.findAll()) {
            bannerList.add(bannerConverter.toDto(banner));
        }
        log.info("Listed all banners.");
        return bannerList;
    }

    public BannerResponse getById(Long id) {
        return bannerConverter.toDto(bannerRepository.getById(id));
    }

    public BannerResponse add(BannerRequest bannerRequest) {
        Banner banner = bannerConverter.toEntity(bannerRequest);
        log.info("Banner has been created.");
        return bannerConverter.toDto(bannerRepository.save(banner));
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
        return bannerConverter.toDto(bannerRepository.save(banner));
    }

    public void deleteById(Long id) {
        getById(id);
        bannerRepository.deleteById(id);
        log.info("Banner has been deleted.");
    }
}