package com.batuhankiltac.emlakburadabanner.service;

import com.batuhankiltac.emlakburadabanner.dto.BannerRequest;
import com.batuhankiltac.emlakburadabanner.dto.BannerResponse;
import com.batuhankiltac.emlakburadabanner.converter.BannerConverter;
import com.batuhankiltac.emlakburadabanner.model.Banner;
import com.batuhankiltac.emlakburadabanner.repository.BannerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BannerServiceTest {

    @InjectMocks
    private BannerService bannerService;

    @Mock
    private BannerRepository bannerRepository;

    @Mock
    private BannerConverter bannerConverter;


    private Banner prepare() {
        Banner banner = new Banner();
        banner.setPhone("12345");
        banner.setQuantity(1);
        return banner;
    }

    private BannerRequest prepareRequest() {
        BannerRequest bannerRequest = new BannerRequest();
        bannerRequest.setPhone("12345");
        bannerRequest.setQuantity(1);
        return bannerRequest;
    }
    private BannerResponse prepareResponse() {
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setAdvertNo(1L);
        bannerResponse.setPhone("12345");
        bannerResponse.setQuantity(1);
        return bannerResponse;
    }

    private List<Banner> prepareBannerList() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(prepare());
        return bannerList;
    }

    @Test
    void addTest() {
        BannerRequest bannerRequest = prepareRequest();

        Mockito
                .when(bannerRepository.save(any()))
                .thenReturn(prepare());

        Mockito
                .when(bannerConverter.toDto(prepare()))
                .thenReturn(prepareResponse());

        BannerResponse bannerResponse = bannerService.add(bannerRequest);
        assertEquals(1, bannerResponse.getQuantity());
    }

    @Test
    void getAllTest() {
        List<BannerResponse> bannerResponseList = bannerService.getAll();

        Mockito
                .when(bannerRepository.findAll())
                .thenReturn(prepareBannerList());

        Mockito
                .when(bannerConverter.toDto(prepare()))
                .thenReturn(prepareResponse());

        assertEquals(0, bannerResponseList.size());
        assertThat(bannerResponseList.size());

        for (BannerResponse response : bannerResponseList) {
            assertThat(response.getQuantity());
            assertEquals("12345", response.getPhone());
            assertEquals(1, response.getQuantity());
        }
    }

    @Test
    void deleteBannerByIdTest() {
        Mockito
                .doNothing().when(bannerRepository)
                .deleteById(1L);

        Mockito
                .when(bannerRepository.findById(1L))
                .thenReturn(Optional.of(prepare()));

        bannerService.deleteById(1L);
        verify(bannerRepository).deleteById(1L);
    }
}