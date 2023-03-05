package com.batuhankiltac.emlakburadapayment.controller;

import com.batuhankiltac.emlakburadapayment.model.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.model.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse add(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.add(paymentRequest);
    }
}