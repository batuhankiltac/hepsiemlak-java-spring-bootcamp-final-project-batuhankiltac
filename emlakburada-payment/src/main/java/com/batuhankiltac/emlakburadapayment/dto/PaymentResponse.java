package com.batuhankiltac.emlakburadapayment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private Long userId;
    private Long productId;
    private Long orderNo;
    private Long amount;
    private Boolean isPay;
}