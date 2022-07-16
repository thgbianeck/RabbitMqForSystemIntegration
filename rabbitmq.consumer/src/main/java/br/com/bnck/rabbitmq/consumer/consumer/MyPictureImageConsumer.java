package br.com.bnck.rabbitmq.consumer.consumer;

import br.com.bnck.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyPictureImageConsumer {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "q.mypicture.image")
    public void listen(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        var picture = objectMapper.readValue(message, Picture.class);

        if(picture.getSize() > 9000) {
            channel.basicReject(tag, false);
            log.error("Message rejected");
//            String messageException = "Picture size too large: " + picture;
//            log.error(messageException, IllegalArgumentException.class);
//            throw new AmqpRejectAndDontRequeueException(messageException);
            return;
        }

        log.info("On Image: {}", picture);
        channel.basicAck(tag, false);
    }
}
