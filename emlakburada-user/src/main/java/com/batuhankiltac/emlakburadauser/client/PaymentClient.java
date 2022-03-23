/*
package com.batuhankiltac.emlakburadauser.client;

import com.batuhankiltac.emlakburadauser.client.request.PaymentRequest;
import com.batuhankiltac.emlakburadauser.client.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// ****** OUT OF USE ******
@FeignClient(name = "payment-feign", url = "http://localhost:8083")
public interface PaymentClient {

    @PostMapping(value = "/payments")
    PaymentResponse add(@RequestBody PaymentRequest paymentRequest);
}*/