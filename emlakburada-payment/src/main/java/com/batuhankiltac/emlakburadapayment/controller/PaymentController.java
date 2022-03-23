package com.batuhankiltac.emlakburadapayment.controller;

import com.batuhankiltac.emlakburadapayment.dto.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.dto.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payments")
    public ResponseEntity<PaymentResponse> add(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.add(paymentRequest), HttpStatus.CREATED);
    }
}