package com.batuhankiltac.emlakburadapayment.controller;

import com.batuhankiltac.emlakburadapayment.dto.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.dto.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/payments")
    public PaymentResponse add(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.add(paymentRequest);
    }
}