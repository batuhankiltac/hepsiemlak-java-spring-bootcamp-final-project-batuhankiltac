package com.batuhankiltac.emlakburadapayment.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long userId;
    private Long productId;
    private Long orderNo;
    private Long amount;
    private Boolean isPay;
}