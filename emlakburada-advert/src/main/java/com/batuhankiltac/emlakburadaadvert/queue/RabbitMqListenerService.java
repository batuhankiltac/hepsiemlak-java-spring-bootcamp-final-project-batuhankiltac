package com.batuhankiltac.emlakburadaadvert.queue;

import com.batuhankiltac.emlakburadaadvert.service.AdvertService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListenerService {
    private final AdvertService advertService;

    @Autowired
    public RabbitMqListenerService(AdvertService advertService) {
        this.advertService = advertService;
    }

    @RabbitListener(queues = "emlakburada.queue")
    public void consume(Long id) {
        System.out.println("Received ID: " + id);
        advertService.changeStatus(id);
    }
}