package com.batuhankiltac.emlakburadapayment.queue;

import com.batuhankiltac.emlakburadapayment.config.RabbitMqConfig;
import com.batuhankiltac.emlakburadapayment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService {
    private final AmqpTemplate rabbitTemplate;
    private final RabbitMqConfig config;

    public void sendToQueue(PaymentRequest paymentRequest) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), paymentRequest);
    }
}