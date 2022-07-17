package br.com.bnck.rabbitmq.producer.two.producer;

import br.com.bnck.rabbitmq.producer.two.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class MultiplePrefetchProducer {

    private final RabbitTemplate rabbitTemplate;

    public MultiplePrefetchProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void simulateTransaction() {
        for (int i = 0; i < 20_000; i++) {
            var message = new DummyMessage("Transaction " + LocalTime.now(), i);
            rabbitTemplate.convertAndSend("x.transaction", "", message);
        }
    }

    public void simulateScheduler() {
        for (int i = 0; i < 200; i++) {
            var message = new DummyMessage("Scheduler " + LocalTime.now(), i);
            rabbitTemplate.convertAndSend("x.scheduler", "", message);
        }
    }
}
