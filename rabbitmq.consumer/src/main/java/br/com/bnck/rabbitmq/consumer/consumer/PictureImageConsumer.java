package br.com.bnck.rabbitmq.consumer.consumer;

import br.com.bnck.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
@Slf4j
@RequiredArgsConstructor
public class PictureImageConsumer {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message) throws IOException {
        var picture = objectMapper.readValue(message, Picture.class);
        log.info("On Image: {}", picture);
    }
}
