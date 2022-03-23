package com.batuhankiltac.emlakburadaadvert.controller;

import com.batuhankiltac.emlakburadaadvert.dto.AdvertRequest;
import com.batuhankiltac.emlakburadaadvert.dto.AdvertResponse;
import com.batuhankiltac.emlakburadaadvert.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertController {
    private final AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping(value = "/adverts")
    public ResponseEntity<List<AdvertResponse>> getAll(){
        return new ResponseEntity<>(advertService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/adverts/users/{id}")
    public ResponseEntity<List<AdvertResponse>> getAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(advertService.getAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/adverts/{id}")
    public ResponseEntity<AdvertResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(advertService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/adverts/users/actives/{userId}")
    public ResponseEntity<List<AdvertResponse>> getAllByUserIdAndStatusTypeActive(@PathVariable Long userId) {
        return new ResponseEntity<>(advertService.getAllByUserIdAndStatusTypeActive(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/adverts/users/passives/{userId}")
    public ResponseEntity<List<AdvertResponse>> getAllByUserIdAndStatusTypePassive(@PathVariable Long userId) {
        return new ResponseEntity<>(advertService.getAllByUserIdAndStatusTypePassive(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> add(@RequestBody AdvertRequest advertRequest) {
        return new ResponseEntity<>(advertService.add(advertRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> update(@RequestBody AdvertRequest advertRequest) {
        return new ResponseEntity<>(advertService.update(advertRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/adverts/status/{id}")
    public ResponseEntity<AdvertResponse> changeStatus(@PathVariable Long id) {
        return new ResponseEntity<>(advertService.isActiveOrIsPassive(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/adverts/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(advertService.deleteById(id), HttpStatus.OK);
    }
}