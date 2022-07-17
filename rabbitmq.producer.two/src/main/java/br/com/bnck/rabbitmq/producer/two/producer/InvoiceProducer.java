package br.com.bnck.rabbitmq.producer.two.producer;

import br.com.bnck.rabbitmq.producer.two.entity.InvoiceCancelledMessage;
import br.com.bnck.rabbitmq.producer.two.entity.InvoiceCreatedMessage;
import br.com.bnck.rabbitmq.producer.two.entity.InvoicePaidMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "x.invoice";

    public InvoiceProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendInvoiceCreated(InvoiceCreatedMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

    public void sendInvoicePaid(InvoicePaidMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

}
