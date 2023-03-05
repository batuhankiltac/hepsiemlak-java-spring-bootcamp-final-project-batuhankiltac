package com.batuhankiltac.emlakburadapayment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentRequest {
    private Long userId;
    private Long productId;
    private Boolean isPay;
    private Long amount;
}