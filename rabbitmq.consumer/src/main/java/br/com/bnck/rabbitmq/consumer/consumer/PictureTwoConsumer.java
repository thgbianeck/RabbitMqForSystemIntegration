package br.com.bnck.rabbitmq.consumer.consumer;

import br.com.bnck.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
@Slf4j
@RequiredArgsConstructor
public class PictureTwoConsumer {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.picture.image", "q.picture.log", "q.picture.vector", "q.picture.filter"})
    public void listen(Message messageAMQP) throws IOException {
        String routingKey = messageAMQP.getMessageProperties().getReceivedRoutingKey();
        var jsonString = new String(messageAMQP.getBody());

        var picture = objectMapper.readValue(jsonString, Picture.class);
        log.info("Consuming picture: {} with routing key: {}", picture, routingKey);
    }
}
