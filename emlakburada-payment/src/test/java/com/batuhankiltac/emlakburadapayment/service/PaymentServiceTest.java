package com.batuhankiltac.emlakburadapayment.service;

import com.batuhankiltac.emlakburadapayment.dto.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.dto.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.converter.PaymentConverter;
import com.batuhankiltac.emlakburadapayment.model.Payment;
import com.batuhankiltac.emlakburadapayment.queue.RabbitMqService;
import com.batuhankiltac.emlakburadapayment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentConverter paymentConverter;

    @Mock
    private RabbitMqService rabbitMqService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void addTest() {
        PaymentRequest paymentRequest = preparePaymentRequest();
        Optional<Payment> payment = Optional.of(preparePayment());

        Mockito
                .when(paymentRepository.findById(paymentRequest.getProductId()))
                .thenReturn(payment);

        Mockito
                .when(paymentRepository.save(any()))
                .thenReturn(preparePayment());

        Mockito
                .when(paymentConverter.toDto(preparePayment()))
                .thenReturn(preparePaymentResponse());

        PaymentResponse paymentResponse = paymentService.add(paymentRequest);
        assertEquals(1, paymentResponse.getUserId());
    }

    private PaymentResponse preparePaymentResponse() {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setUserId(1L);
        paymentResponse.setOrderNo(12345L);
        paymentResponse.setAmount(1435L);
        return paymentResponse;
    }

    private PaymentRequest preparePaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(1L);
        paymentRequest.setIsPay(true);
        return paymentRequest;
    }

    private Payment preparePayment() {
        Payment payment = new Payment();
        payment.setUserId(1L);
        payment.setOrderNo(12345L);
        payment.setAmount(1435L);
        return payment;
    }
}