package br.com.bnck.rabbitmq.producer.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FixedRateProducer {

    private final RabbitTemplate rabbitTemplate;

    private int i = 0;

    @Scheduled(fixedRate = 500)
    public void sendMessage() {
        i++;
//        log.info("i is {}", i);
        rabbitTemplate.convertAndSend("course.fixedrate", "Fixed rate " + i);
    }
}
