package br.com.bnck.rabbitmq.producer.producer;

import br.com.bnck.rabbitmq.producer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SpringEmployeeProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public SpringEmployeeProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Employee data) throws IOException {
        var json = objectMapper.writeValueAsString(data);

        rabbitTemplate.convertAndSend("x.spring2.work", "", json);
    }

}
