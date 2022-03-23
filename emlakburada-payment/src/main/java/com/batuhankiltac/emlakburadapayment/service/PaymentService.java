package com.batuhankiltac.emlakburadapayment.service;

import com.batuhankiltac.emlakburadapayment.dto.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.dto.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.mapper.PaymentMapper;
import com.batuhankiltac.emlakburadapayment.model.Payment;
import com.batuhankiltac.emlakburadapayment.queue.RabbitMqService;
import com.batuhankiltac.emlakburadapayment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final RabbitMqService rabbitMqService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, RabbitMqService rabbitMqService) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.rabbitMqService = rabbitMqService;
    }

    public PaymentResponse add(PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.toEntity(paymentRequest);
        log.info("Payment has been success.");
        PaymentResponse response = paymentMapper.toDto(paymentRepository.save(payment));
        rabbitMqService.sendToQueue(paymentRequest);
        return response;
    }
}