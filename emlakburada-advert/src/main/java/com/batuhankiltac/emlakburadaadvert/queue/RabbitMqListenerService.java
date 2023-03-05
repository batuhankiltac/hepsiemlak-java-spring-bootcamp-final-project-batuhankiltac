package com.batuhankiltac.emlakburadaadvert.queue;

import com.batuhankiltac.emlakburadaadvert.service.AdvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqListenerService {
    private final AdvertService advertService;

    @RabbitListener(queues = "emlakburada.queue")
    public void consume(Long id) {
        log.info("Received ID: " + id);
        advertService.changeStatusToActive(id);
    }
}