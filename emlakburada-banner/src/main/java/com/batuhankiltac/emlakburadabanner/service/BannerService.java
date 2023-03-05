package com.batuhankiltac.emlakburadabanner.service;

import com.batuhankiltac.emlakburadabanner.converter.BannerConverter;
import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import com.batuhankiltac.emlakburadabanner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;
    private final BannerConverter bannerConverter;

    public List<BannerResponse> getAll() {
        return bannerRepository.findAll().stream()
                .map(bannerConverter::convert)
                .collect(Collectors.toList());
    }

    public BannerResponse getById(Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow(() -> new RuntimeException("Banner not found."));
        return bannerConverter.convert(banner);
    }

    public BannerResponse add(BannerRequest bannerRequest) {
        Banner banner = bannerConverter.convert(bannerRequest);
        log.info("Banner has been created.");
        return bannerConverter.convert(bannerRepository.save(banner));
    }

    public void update(BannerRequest bannerRequest, Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow(() -> new RuntimeException("Banner not found."));
        banner.setTitle(bannerRequest.getTitle());
        banner.setPhone(bannerRequest.getPhone());
        banner.setQuantity(banner.getQuantity());
        bannerRepository.save(banner);
        log.info("Banner has been updated.");
    }

    public void deleteById(Long id) {
        bannerRepository.deleteById(id);
        log.info("Banner has been deleted.");
    }
}