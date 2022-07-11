package br.com.bnck.rabbitmq.producer.producer;

import br.com.bnck.rabbitmq.producer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
@Slf4j
@RequiredArgsConstructor
public class PictureProducerTwo {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(Picture picture) throws IOException {
        var json = objectMapper.writeValueAsString(picture);

        //1st word picture source

        final String sb = picture.getSource() + "." +

        //2nd word is based on picture size
        (picture.getSize() > 4000 ? "large" : "small") + "." +

        // 3rd is picture type
        picture.getType();

        rabbitTemplate.convertAndSend("x.picture2", sb, json);
        log.info("Picture send to queue: Exchange: x.picture2, bind: {}, body: {}", sb, json);
    }
}
