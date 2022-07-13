package com.batuhankiltac.emlakburadaadvert.service;

import com.batuhankiltac.emlakburadaadvert.client.BannerClient;
import com.batuhankiltac.emlakburadaadvert.client.UserClient;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.converter.AdvertConverter;
import com.batuhankiltac.emlakburadaadvert.model.Advert;
import com.batuhankiltac.emlakburadaadvert.queue.RabbitMqService;
import com.batuhankiltac.emlakburadaadvert.repository.AdvertRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AdvertServiceTest {

    @InjectMocks
    private AdvertService advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private AdvertConverter advertConverter;

    @Mock
    private BannerClient bannerClient;

    @Mock
    private UserClient userClient;

    @Mock
    private RabbitMqService rabbitMqService;

    private Advert prepare() {
        Advert advert = new Advert();
        advert.setId(1L);
        advert.setUserId(1L);
        advert.setTitle("merhaba");
        advert.setDescription("dünya");
        return advert;
    }

    private AdvertRequest prepareRequest() {
        AdvertRequest advertRequest = new AdvertRequest();
        advertRequest.setUserId(1L);
        advertRequest.setTitle("merhaba");
        advertRequest.setDescription("dünya");
        return advertRequest;
    }

    private AdvertResponse prepareResponse() {
        AdvertResponse response = new AdvertResponse();
        response.setUserId(1L);
        response.setTitle("test-title");
        response.setDescription("test-description");
        return response;
    }

    private List<Advert> prepareList() {
        List<Advert> advertList = new ArrayList<>();
        advertList.add(prepare());
        return advertList;
    }

    @Test
    void getAllTest() {
        Mockito
                .when(advertRepository.findAll())
                .thenReturn(prepareList());

        Mockito
                .when(advertConverter.toDto(prepare()))
                .thenReturn(prepareResponse());

        Mockito
                .when(advertConverter.toEntity(prepareRequest()))
                .thenReturn(prepare());

        List<AdvertResponse> responseList = advertService.getAll();
        assertNotEquals(0, responseList.size());
        assertThat(responseList.size()).isNotZero();

        for (AdvertResponse response : responseList) {
            assertThat(response.getUserId());
            assertNotEquals("123123", response.getTitle());
        }
    }

    @Test
    void deleteByIdTest() {
        Mockito
                .doNothing().when(advertRepository)
                .deleteById(1L);

        Mockito
                .when(advertRepository.findById(1L))
                .thenReturn(Optional.of(prepare()));

        advertService.deleteById(1L);
        verify(advertRepository).deleteById(1L);
    }
}