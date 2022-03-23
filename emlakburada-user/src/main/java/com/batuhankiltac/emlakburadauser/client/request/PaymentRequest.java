package com.batuhankiltac.emlakburadauser.client.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long userId;
    private Long productId;
    private Boolean isPay;
    private Long amount;
}