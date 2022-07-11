package br.com.bnck.rabbitmq.producer.producer;

import br.com.bnck.rabbitmq.producer.entity.Furniture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FurnitureProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(Furniture furniture) throws IOException {
        var messageProperties = new MessageProperties();
        messageProperties.setHeader("color", furniture.getColor());
        messageProperties.setHeader("material", furniture.getMaterial());

        var json = objectMapper.writeValueAsString(furniture);

        var message = new Message(json.getBytes(), messageProperties);

        rabbitTemplate.send("x.promotion","", message);
        log.info("Furniture: {}, send to Exchange: x.promotion", json);
    }
}
