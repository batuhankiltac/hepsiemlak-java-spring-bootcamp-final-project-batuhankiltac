package com.batuhankiltac.emlakburadauser.queue;

import com.batuhankiltac.emlakburadauser.client.request.PaymentRequest;
import com.batuhankiltac.emlakburadauser.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListenerService {
    private final UserService userService;

    @Autowired
    public RabbitMqListenerService(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "emlakburada.payment")
    public void consume(PaymentRequest paymentRequest) {
        System.out.println("Received Data: " + paymentRequest);

        if (paymentRequest != null) {
            userService.updateQuantitiesAndDates(paymentRequest.getUserId(), paymentRequest.getProductId());
        }
    }
}