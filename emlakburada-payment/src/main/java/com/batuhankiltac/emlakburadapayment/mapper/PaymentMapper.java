package com.batuhankiltac.emlakburadapayment.mapper;

import com.batuhankiltac.emlakburadapayment.dto.PaymentRequest;
import com.batuhankiltac.emlakburadapayment.dto.PaymentResponse;
import com.batuhankiltac.emlakburadapayment.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

@Slf4j
@Mapper(componentModel = "spring")
public class PaymentMapper {
    private Long orderNo = 98766789L;

    public PaymentResponse toDto(Payment payment) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setOrderNo(payment.getOrderNo());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setIsPay(payment.getIsPay());
        paymentResponse.setProductId(payment.getProductId());
        paymentResponse.setUserId(payment.getUserId());
        return paymentResponse;
    }

    public Payment toEntity(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setOrderNo(orderNo++);
        payment.setIsPay(paymentRequest.getIsPay());
        payment.setUserId(paymentRequest.getUserId());
        payment.setProductId(paymentRequest.getProductId());
        payment.setAmount(paymentRequest.getAmount());
        return payment;
    }
}