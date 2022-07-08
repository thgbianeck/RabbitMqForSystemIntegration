package br.com.bnck.rabbitmq.producer.producer;

import br.com.bnck.rabbitmq.producer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
@RequiredArgsConstructor
public class EmployeeJsonProducer {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(Employee data) throws IOException {
        var json = objectMapper.writeValueAsString(data);
        rabbitTemplate.convertAndSend("course.employee", json);
    }
}
