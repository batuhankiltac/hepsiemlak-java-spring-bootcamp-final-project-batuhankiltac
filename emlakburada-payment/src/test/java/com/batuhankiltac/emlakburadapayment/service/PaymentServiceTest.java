package com.batuhankiltac.emlakburadapayment.service;

import com.batuhankiltac.emlakburadapayment.converter.PaymentConverter;
import com.batuhankiltac.emlakburadapayment.domain.Payment;
import com.batuhankiltac.emlakburadapayment.model.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.queue.RabbitMqService;
import com.batuhankiltac.emlakburadapayment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentConverter paymentConverter;

    @Mock
    private RabbitMqService rabbitMqService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void it_should_add_payment() {
        // Given
        PaymentRequest paymentRequest = PaymentRequest.builder().build();
        Payment payment = Payment.builder().build();
        when(paymentConverter.convert(paymentRequest)).thenReturn(payment);

        // WHen
        paymentService.add(paymentRequest);

        // Then
        verify(paymentConverter).convert(paymentRequest);
        verify(rabbitMqService).sendToQueue(paymentRequest);
        verify(paymentRepository).save(payment);
    }
}