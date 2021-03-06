package com.batuhankiltac.emlakburadaadvert.service;

import com.batuhankiltac.emlakburadaadvert.client.BannerClient;
import com.batuhankiltac.emlakburadaadvert.client.UserClient;
import com.batuhankiltac.emlakburadaadvert.client.request.BannerRequest;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.exception.QuantityNotFoundException;
import com.batuhankiltac.emlakburadaadvert.exception.UserNotFoundException;
import com.batuhankiltac.emlakburadaadvert.converter.AdvertConverter;
import com.batuhankiltac.emlakburadaadvert.model.Advert;
import com.batuhankiltac.emlakburadaadvert.model.enums.StatusType;
import com.batuhankiltac.emlakburadaadvert.queue.RabbitMqService;
import com.batuhankiltac.emlakburadaadvert.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<AdvertResponse> advertList = new ArrayList<>();
        for (Advert advert : advertRepository.findAll()) {
            advertList.add(advertConverter.toDto(advert));
        }
        log.info("Listed all users.");
        return advertList;
    }

    public List<AdvertResponse> getAllByUserIdAndStatusTypeActive(Long userId) {
        List<AdvertResponse> activeList = new ArrayList<>();
        for (Advert advert : advertRepository.findAllByUserIdAndStatusType(userId, StatusType.ACTIVE)) {
            activeList.add(advertConverter.toDto(advert));
        }
        return activeList;
    }

    public List<AdvertResponse> getAllByUserIdAndStatusTypePassive(Long userId) {
        List<AdvertResponse> activeList = new ArrayList<>();
        for (Advert advert : advertRepository.findAllByUserIdAndStatusType(userId, StatusType.PASSIVE)) {
            activeList.add(advertConverter.toDto(advert));
        }
        return activeList;
    }

    public List<AdvertResponse> getAllByUserId(Long id) {
        List<AdvertResponse> advertList = new ArrayList<>();
        for (Advert advert : advertRepository.getAllByUserId(id)) {
            advertList.add(advertConverter.toDto(advert));
        }
        return advertList;
    }

    public AdvertResponse getById(Long id) {
        return advertConverter.toDto(advertRepository.getById(id));
    }

    public AdvertResponse add(AdvertRequest advertRequest) {
        if (userClient.getById(advertRequest.getUserId()) != null) {
            if (userClient.getIdIfQuantityExist(advertRequest.getUserId()) != null) {
                Advert advert = advertConverter.toEntity(advertRequest);
                BannerRequest bannerRequest = new BannerRequest();
                bannerRequest.setAdvertNo(advertRequest.getAdvertNo());
                bannerRequest.setTitle(advertRequest.getTitle());
                bannerClient.addBanner(bannerRequest);
                Advert adv = advertRepository.save(advert);
                rabbitMqService.sendToQueue(adv.getId());
                log.info("Advert has been created.");
                return advertConverter.toDto(adv);
            } else {
                log.info("User has not enough quantity!");
                throw new QuantityNotFoundException("User has not enough quantity!");
            }
        } else {
            log.info("User ID not found!");
            throw new UserNotFoundException("User not found!");
        }
    }

    public AdvertResponse update(AdvertRequest advertRequest) {
        advertRepository.findById(advertRequest.getUserId());
        Advert advert = Advert.builder()
                .userId(advertRequest.getUserId())
                .advertType(advertRequest.getAdvertType())
                .realEstateType(advertRequest.getRealEstateType())
                .title(advertRequest.getTitle())
                .description(advertRequest.getDescription())
                .build();
        log.info("Advert has been updated!");
        return advertConverter.toDto(advertRepository.save(advert));
    }

    public void deleteById(Long id) {
        getById(id);
        advertRepository.deleteById(id);
        log.info("Advert has been deleted.");
    }

    public void changeStatus(Long id) {
        Advert advert = advertRepository.getById(id);
        advert.setStatusType(StatusType.ACTIVE);
        log.info("Advert status has been changed to " + advert.getStatusType());
        advertRepository.save(advert);
    }

    public AdvertResponse isActiveOrIsPassive(Long id) {
        Advert advert = advertRepository.getById(id);
        if (advert.getStatusType().equals(StatusType.ACTIVE)) {
            advert.setStatusType(StatusType.PASSIVE);
        } else {
            advert.setStatusType(StatusType.ACTIVE);
        }
        return advertConverter.toDto(advertRepository.save(advert));
    }
}