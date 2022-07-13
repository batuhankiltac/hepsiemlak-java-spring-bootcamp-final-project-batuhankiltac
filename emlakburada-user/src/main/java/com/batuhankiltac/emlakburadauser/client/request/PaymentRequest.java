package com.batuhankiltac.emlakburadauser.client.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private Long userId;
    private Long productId;
    private Boolean isPay;
    private Long amount;
}