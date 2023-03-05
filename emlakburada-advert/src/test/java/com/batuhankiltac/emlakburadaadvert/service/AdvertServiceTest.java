package com.batuhankiltac.emlakburadaadvert.service;

import com.batuhankiltac.emlakburadaadvert.client.BannerClient;
import com.batuhankiltac.emlakburadaadvert.client.UserClient;
import com.batuhankiltac.emlakburadaadvert.converter.AdvertConverter;
import com.batuhankiltac.emlakburadaadvert.domain.Advert;
import com.batuhankiltac.emlakburadaadvert.domain.enums.StatusType;
import com.batuhankiltac.emlakburadaadvert.model.request.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.model.response.UserResponse;
import com.batuhankiltac.emlakburadaadvert.queue.RabbitMqService;
import com.batuhankiltac.emlakburadaadvert.repository.AdvertRepository;
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
class AdvertServiceTest {

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

    @Test
    void it_should_get_all_adverts() {
        // Given
        Advert advert = Advert.builder().build();
        List<Advert> adverts = Collections.singletonList(advert);
        when(advertRepository.findAll()).thenReturn(adverts);

        // When
        advertService.getAll();

        // Then
        verify(advertRepository).findAll();
    }

    @Test
    void it_should_get_all_by_user_id_and_status_type_active() {
        // Given
        Advert advert = Advert.builder()
                .userId(1L)
                .build();
        List<Advert> adverts = Collections.singletonList(advert);
        when(advertRepository.findAllByUserIdAndStatusType(advert.getUserId(), StatusType.ACTIVE)).thenReturn(adverts);

        // When
        advertService.getAllByUserIdAndStatusTypeActive(advert.getUserId());

        // Then
        verify(advertRepository).findAllByUserIdAndStatusType(advert.getUserId(), StatusType.ACTIVE);
    }

    @Test
    void it_should_get_all_by_user_id_and_status_type_passive() {
        // Given
        Advert advert = Advert.builder()
                .userId(1L)
                .build();
        List<Advert> adverts = Collections.singletonList(advert);
        when(advertRepository.findAllByUserIdAndStatusType(advert.getUserId(), StatusType.PASSIVE)).thenReturn(adverts);

        // When
        advertService.getAllByUserIdAndStatusTypePassive(advert.getUserId());

        // Then
        verify(advertRepository).findAllByUserIdAndStatusType(advert.getUserId(), StatusType.PASSIVE);
    }

    @Test
    void it_should_get_all_by_user_id() {
        // Given
        Advert advert = Advert.builder()
                .userId(1L)
                .build();
        List<Advert> adverts = Collections.singletonList(advert);
        when(advertRepository.getAllByUserId(advert.getUserId())).thenReturn(adverts);

        // When
        advertService.getAllByUserId(advert.getUserId());

        // Then
        verify(advertRepository).getAllByUserId(advert.getUserId());
    }

    @Test
    void it_should_get_by_id() {
        // Given
        Advert advert = Advert.builder()
                .id(1L)
                .build();
        when(advertRepository.getById(advert.getId())).thenReturn(advert);

        // When
        advertService.getById(advert.getId());

        // Then
        verify(advertRepository).getById(advert.getId());
    }

    @Test
    void it_should_add_advert() {
        // Given
        AdvertRequest advertRequest = AdvertRequest.builder()
                .userId(1L)
                .advertNo(123L)
                .title("test-title")
                .build();
        Advert advert = Advert.builder().build();
        when(advertConverter.convert(advertRequest)).thenReturn(advert);
        UserResponse userResponse = UserResponse.builder()
                .id(advertRequest.getUserId())
                .quantities(10)
                .build();
        when(userClient.getById(advertRequest.getUserId())).thenReturn(userResponse);

        // When
        advertService.add(advertRequest);

        // Then
        verify(userClient).getById(advertRequest.getUserId());
        verify(advertConverter).convert(advertRequest);
        verify(rabbitMqService).sendToQueue(advert.getId());
    }

    @Test
    void it_should_update() {
        // Given
        Long id = 1L;
        AdvertRequest advertRequest = AdvertRequest.builder().build();
        Advert advert = Advert.builder().build();
        when(advertRepository.findById(id)).thenReturn(Optional.of(advert));

        // When
        advertService.update(advertRequest, id);

        // Then
        verify(advertRepository).findById(id);
        verify(advertRepository).save(advert);
    }

    @Test
    void it_should_delete() {
        // Given
        Long advertId = 1L;

        // When
        advertService.deleteById(advertId);

        // Then
        verify(advertRepository).deleteById(advertId);
    }

    @Test
    void it_should_change_status_to_active() {
        // Given
        Long advertId = 1L;
        Advert advert = Advert.builder().build();
        when(advertRepository.findById(advertId)).thenReturn(Optional.of(advert));

        // When
        advertService.changeStatusToActive(advertId);

        // Then
        verify(advertRepository).findById(advertId);
        verify(advertRepository).save(advert);
    }

    @Test
    void it_should_change_status_to_passive_when_status_is_active() {
        // Given
        Long advertId = 1L;
        Advert advert = Advert.builder()
                .statusType(StatusType.ACTIVE)
                .build();
        when(advertRepository.findById(advertId)).thenReturn(Optional.of(advert));

        // When
        advertService.changeStatusToEachOther(advertId);

        // Then
        verify(advertRepository).findById(advertId);
        verify(advertRepository).save(advert);
    }

    @Test
    void it_should_change_status_to_active_when_status_is_passive() {
        // Given
        Long advertId = 1L;
        Advert advert = Advert.builder()
                .statusType(StatusType.PASSIVE)
                .build();
        when(advertRepository.findById(advertId)).thenReturn(Optional.of(advert));

        // When
        advertService.changeStatusToEachOther(advertId);

        // Then
        verify(advertRepository).findById(advertId);
        verify(advertRepository).save(advert);
    }
}