package com.batuhankiltac.emlakburadauser.client.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long userId;
    private Long productId;
    private Long orderNo;
    private Long amount;
    private Boolean isPay;
}