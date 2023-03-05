package com.batuhankiltac.emlakburadabanner.service;

import com.batuhankiltac.emlakburadabanner.converter.BannerConverter;
import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import com.batuhankiltac.emlakburadabanner.repository.BannerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BannerServiceTest {

    @InjectMocks
    private BannerService bannerService;

    @Mock
    private BannerRepository bannerRepository;

    @Mock
    private BannerConverter bannerConverter;

    @Test
    void it_should_get_all() {
        // Given
        Banner banner = Banner.builder().build();
        List<Banner> banners = Collections.singletonList(banner);
        when(bannerRepository.findAll()).thenReturn(banners);

        // When
        bannerService.getAll();

        // Then
        verify(bannerRepository).findAll();
    }

    @Test
    void it_should_get_by_id() {
        // Given
        Long id = 1L;
        Banner banner = Banner.builder().build();
        when(bannerRepository.findById(id)).thenReturn(Optional.of(banner));
        BannerResponse bannerResponse = BannerResponse.builder().build();
        when(bannerConverter.convert(banner)).thenReturn(bannerResponse);

        // When
        bannerService.getById(id);

        // Then
        verify(bannerRepository).findById(id);
        verify(bannerConverter).convert(banner);
    }

    @Test
    void it_should_add_banner() {
        // Given
        BannerRequest bannerRequest = BannerRequest.builder().build();
        Banner banner = Banner.builder().build();
        when(bannerConverter.convert(bannerRequest)).thenReturn(banner);
        BannerResponse bannerResponse = BannerResponse.builder().build();
        when(bannerConverter.convert(banner)).thenReturn(bannerResponse);

        // When
        bannerService.add(bannerRequest);

        // Then
        verify(bannerConverter).convert(bannerRequest);
        verify(bannerRepository).save(banner);
    }

    @Test
    void it_should_update() {
        // Given
        Long id = 1L;
        BannerRequest bannerRequest = BannerRequest.builder().build();
        Banner banner = Banner.builder().build();
        when(bannerRepository.findById(id)).thenReturn(Optional.of(banner));
        BannerResponse bannerResponse = BannerResponse.builder().build();
        when(bannerConverter.convert(banner)).thenReturn(bannerResponse);

        // When
        bannerService.update(bannerRequest, id);

        // Then
        verify(bannerRepository).findById(id);
        verify(bannerRepository).save(banner);
    }

    @Test
    void it_should_delete() {
        // Given
        Long id = 1L;

        // When
        bannerService.deleteById(id);

        // Then
        verify(bannerRepository).deleteById(id);
    }
}