package br.com.bnck.rabbitmq.producer.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloRabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendHello(String name) {
        rabbitTemplate.convertAndSend("course.hello", "Hello" + name);

    }
}
