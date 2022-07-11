package br.com.bnck.rabbitmq.consumer.consumer;

import br.com.bnck.rabbitmq.consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
@Slf4j
@RequiredArgsConstructor
public class AccountingConsumer {
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "q.hr.accounting")
    public void listen(String message) throws IOException {
        var employee = objectMapper.readValue(message, Employee.class);
        log.info("Employee on Accounting is: {}", employee.toString());
    }
}
