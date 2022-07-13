package com.batuhankiltac.emlakburadaadvert.controller;

import com.batuhankiltac.emlakburadaadvert.dto.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping(value = "/adverts")
    public List<AdvertResponse> getAll(){
        return advertService.getAll();
    }

    @GetMapping(value = "/adverts/users/{id}")
    public List<AdvertResponse> getAllByUserId(@PathVariable Long id) {
        return advertService.getAllByUserId(id);
    }

    @GetMapping(value = "/adverts/{id}")
    public AdvertResponse getById(@PathVariable Long id) {
        return advertService.getById(id);
    }

    @GetMapping(value = "/adverts/users/actives/{userId}")
    public List<AdvertResponse> getAllByUserIdAndStatusTypeActive(@PathVariable Long userId) {
        return advertService.getAllByUserIdAndStatusTypeActive(userId);
    }

    @GetMapping(value = "/adverts/users/passives/{userId}")
    public List<AdvertResponse> getAllByUserIdAndStatusTypePassive(@PathVariable Long userId) {
        return advertService.getAllByUserIdAndStatusTypePassive(userId);
    }

    @PostMapping(value = "/adverts")
    public AdvertResponse add(@RequestBody AdvertRequest advertRequest) {
        return advertService.add(advertRequest);
    }

    @PutMapping(value = "/adverts")
    public AdvertResponse update(@RequestBody AdvertRequest advertRequest) {
        return advertService.update(advertRequest);
    }

    @PutMapping(value = "/adverts/status/{id}")
    public AdvertResponse changeStatus(@PathVariable Long id) {
        return advertService.isActiveOrIsPassive(id);
    }

    @DeleteMapping(value = "/adverts/{id}")
    public void deleteById(@PathVariable Long id) {
        advertService.deleteById(id);
    }
}