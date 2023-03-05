package com.batuhankiltac.emlakburadapayment.converter;

import com.batuhankiltac.emlakburadapayment.model.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.model.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
    private Long orderNo = 98766789L;

    public PaymentResponse convert(Payment payment) {
        return PaymentResponse.builder()
                .orderNo(payment.getOrderNo())
                .amount(payment.getAmount())
                .isPay(payment.getIsPay())
                .productId(payment.getProductId())
                .userId(payment.getUserId())
                .build();
    }

    public Payment convert(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderNo(orderNo++)
                .isPay(paymentRequest.getIsPay())
                .userId(paymentRequest.getUserId())
                .productId(paymentRequest.getProductId())
                .amount(paymentRequest.getAmount())
                .build();
    }
}