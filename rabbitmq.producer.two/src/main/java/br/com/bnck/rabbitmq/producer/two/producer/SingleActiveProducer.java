package br.com.bnck.rabbitmq.producer.two.producer;

import br.com.bnck.rabbitmq.producer.two.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SingleActiveProducer {

    private final RabbitTemplate rabbitTemplate;

    public SingleActiveProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDummy() {
        for (int i = 0; i < 10_000; i++) {
            var message = new DummyMessage("Message " + i, i);
            rabbitTemplate.convertAndSend("x.single", "", message);
        }
    }
}
