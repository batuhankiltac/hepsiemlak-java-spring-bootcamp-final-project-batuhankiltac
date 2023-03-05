package com.batuhankiltac.emlakburadauser.queue;

import com.batuhankiltac.emlakburadauser.model.request.PaymentRequest;
import com.batuhankiltac.emlakburadauser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitMqListenerService {
    private final UserService userService;

    @RabbitListener(queues = "emlakburada.payment")
    public void consume(PaymentRequest paymentRequest) {
        log.info("Received Data: " + paymentRequest);

        if (paymentRequest != null) {
            userService.updateQuantitiesAndDates(paymentRequest.getUserId(), paymentRequest.getProductId());
        }
    }
}