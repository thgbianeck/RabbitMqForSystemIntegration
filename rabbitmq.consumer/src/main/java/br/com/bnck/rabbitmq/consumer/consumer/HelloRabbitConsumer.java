package br.com.bnck.rabbitmq.consumer.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Service
@Slf4j
@RequiredArgsConstructor
public class HelloRabbitConsumer {

    @RabbitListener(queues = "course.hello")
    private void listen(String message) {
        log.info("Consuming {}", message);
    }
}
