package com.batuhankiltac.emlakburadaadvert.service;

import com.batuhankiltac.emlakburadaadvert.client.BannerClient;
import com.batuhankiltac.emlakburadaadvert.client.UserClient;
import com.batuhankiltac.emlakburadaadvert.converter.AdvertConverter;
import com.batuhankiltac.emlakburadaadvert.domain.Advert;
import com.batuhankiltac.emlakburadaadvert.domain.enums.StatusType;
import com.batuhankiltac.emlakburadaadvert.exception.GlobalException;
import com.batuhankiltac.emlakburadaadvert.exception.QuantityNotFoundException;
import com.batuhankiltac.emlakburadaadvert.model.request.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.model.request.BannerRequest;
import com.batuhankiltac.emlakburadaadvert.model.response.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.model.response.UserResponse;
import com.batuhankiltac.emlakburadaadvert.queue.RabbitMqService;
import com.batuhankiltac.emlakburadaadvert.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertConverter advertConverter;
    private final BannerClient bannerClient;
    private final UserClient userClient;
    private final RabbitMqService rabbitMqService;

    public List<AdvertResponse> getAll() {
        return advertRepository.findAll().stream()
                .map(advertConverter::convert)
                .collect(Collectors.toList());
    }

    public List<AdvertResponse> getAllByUserIdAndStatusTypeActive(Long userId) {
        return advertRepository.findAllByUserIdAndStatusType(userId, StatusType.ACTIVE).stream()
                .map(advertConverter::convert)
                .collect(Collectors.toList());
    }

    public List<AdvertResponse> getAllByUserIdAndStatusTypePassive(Long userId) {
        return advertRepository.findAllByUserIdAndStatusType(userId, StatusType.PASSIVE).stream()
                .map(advertConverter::convert)
                .collect(Collectors.toList());
    }

    public List<AdvertResponse> getAllByUserId(Long userId) {
        return advertRepository.getAllByUserId(userId).stream()
                .map(advertConverter::convert)
                .collect(Collectors.toList());
    }

    public AdvertResponse getById(Long id) {
        return advertConverter.convert(advertRepository.getById(id));
    }

    public AdvertResponse add(AdvertRequest advertRequest) {
        UserResponse userResponse = userClient.getById(advertRequest.getUserId());
        Advert advert = advertConverter.convert(advertRequest);

        if (Objects.nonNull(userResponse.getId())) {
            assertIfUserHasQuantity(userResponse.getQuantities());
            bannerClient.addBanner(BannerRequest.builder()
                                           .advertNo(advertRequest.getAdvertNo())
                                           .title(advertRequest.getTitle())
                                           .build());
            rabbitMqService.sendToQueue(advert.getId());
            log.info("Advert has been created.");
        }
        return AdvertResponse.builder()
                .userId(advert.getUserId())
                .advertNo(advert.getAdvertNo())
                .advertType(advert.getAdvertType())
                .realEstateType(advert.getRealEstateType())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .build();
    }

    public void update(AdvertRequest advertRequest, Long id) {
        Advert advert = advertRepository.findById(id).orElseThrow(() -> new GlobalException("Advert not found."));
        advert.setUserId(advertRequest.getUserId());
        advert.setAdvertNo(advertRequest.getAdvertNo());
        advert.setAdvertType(advertRequest.getAdvertType());
        advert.setRealEstateType(advertRequest.getRealEstateType());
        advert.setTitle(advertRequest.getTitle());
        advert.setDescription(advertRequest.getDescription());
        log.info("Advert has been updated!");
        advertRepository.save(advert);
    }

    public void deleteById(Long id) {
        advertRepository.deleteById(id);
        log.info("Advert has been deleted.");
    }

    public void changeStatusToActive(Long id) {
        Advert advert = advertRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Advert not found."));
        advert.setStatusType(StatusType.ACTIVE);
        log.info("Advert status has been changed to " + advert.getStatusType());
        advertRepository.save(advert);
    }

    public AdvertResponse changeStatusToEachOther(Long id) {
        Advert advert = advertRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Advert not found."));
        if (StatusType.ACTIVE.equals(advert.getStatusType())) {
            advert.setStatusType(StatusType.PASSIVE);
        } else {
            advert.setStatusType(StatusType.ACTIVE);
        }
        return advertConverter.convert(advertRepository.save(advert));
    }

    private void assertIfUserHasQuantity(Integer quantities) {
        if (Boolean.FALSE.equals(quantities > 0)) {
            throw new QuantityNotFoundException("Not enough quantities.");
        }
    }
}