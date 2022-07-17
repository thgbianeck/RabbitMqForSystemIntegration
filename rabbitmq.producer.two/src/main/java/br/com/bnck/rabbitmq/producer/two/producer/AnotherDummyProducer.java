package br.com.bnck.rabbitmq.producer.two.producer;

import br.com.bnck.rabbitmq.producer.two.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnotherDummyProducer {

    private final RabbitTemplate rabbitTemplate;

    public AnotherDummyProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDummy(DummyMessage message) {
        rabbitTemplate.convertAndSend("x.another-dummy", "", message);
    }

}
