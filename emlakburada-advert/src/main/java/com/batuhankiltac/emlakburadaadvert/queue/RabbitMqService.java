package com.batuhankiltac.emlakburadaadvert.queue;

import com.batuhankiltac.emlakburadaadvert.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {
    private final AmqpTemplate rabbitTemplate;
    private final RabbitMqConfig config;

    @Autowired
    public RabbitMqService(AmqpTemplate rabbitTemplate, RabbitMqConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.config = config;
    }

    public void sendToQueue(Long id) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), id);
    }
}