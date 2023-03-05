package com.batuhankiltac.emlakburadaadvert.controller;

import com.batuhankiltac.emlakburadaadvert.model.request.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.model.response.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/adverts")
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping
    public List<AdvertResponse> getAll(){
        return advertService.getAll();
    }

    @GetMapping(value = "/all/{userId}")
    public List<AdvertResponse> getAllByUserId(@PathVariable Long userId) {
        return advertService.getAllByUserId(userId);
    }

    @GetMapping(value = "/{id}")
    public AdvertResponse getById(@PathVariable Long id) {
        return advertService.getById(id);
    }

    @GetMapping(value = "/all/actives/{userId}")
    public List<AdvertResponse> getAllByUserIdAndStatusTypeActive(@PathVariable Long userId) {
        return advertService.getAllByUserIdAndStatusTypeActive(userId);
    }

    @GetMapping(value = "/all/passives/{userId}")
    public List<AdvertResponse> getAllByUserIdAndStatusTypePassive(@PathVariable Long userId) {
        return advertService.getAllByUserIdAndStatusTypePassive(userId);
    }

    @PostMapping
    public AdvertResponse add(@RequestBody AdvertRequest advertRequest) {
        return advertService.add(advertRequest);
    }

    @PutMapping
    public void update(@RequestBody AdvertRequest advertRequest, @RequestParam Long id) {
        advertService.update(advertRequest, id);
    }

    @PutMapping(value = "/status/{id}")
    public AdvertResponse changeStatusToEachOther(@PathVariable Long id) {
        return advertService.changeStatusToEachOther(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        advertService.deleteById(id);
    }
}