package com.batuhankiltac.emlakburadaadvert.queue;

import com.batuhankiltac.emlakburadaadvert.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService {
    private final AmqpTemplate rabbitTemplate;
    private final RabbitMqConfig config;

    public void sendToQueue(Long id) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), id);
    }
}