package br.com.bnck.rabbitmq.producer.producer;

import br.com.bnck.rabbitmq.producer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetryPictureProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(Picture picture) throws IOException {
        var json = objectMapper.writeValueAsString(picture);
        log.info("Picture: {}, converted to Json: {}", picture.toString(), json);
        rabbitTemplate.convertAndSend("x.guideline.work",picture.getType(), json);
    }
}
