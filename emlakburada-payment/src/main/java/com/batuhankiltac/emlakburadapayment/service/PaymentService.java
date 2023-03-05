package com.batuhankiltac.emlakburadapayment.service;

import com.batuhankiltac.emlakburadapayment.converter.PaymentConverter;
import com.batuhankiltac.emlakburadapayment.model.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.model.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.domain.Payment;
import com.batuhankiltac.emlakburadapayment.queue.RabbitMqService;
import com.batuhankiltac.emlakburadapayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentConverter paymentConverter;
    private final RabbitMqService rabbitMqService;

    public PaymentResponse add(PaymentRequest paymentRequest) {
        Payment payment = paymentConverter.convert(paymentRequest);
        log.info("Payment has been success.");
        rabbitMqService.sendToQueue(paymentRequest);
        return paymentConverter.convert(paymentRepository.save(payment));
    }
}