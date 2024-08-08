package com.techchallenge.pagamento.application.events;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.configuration.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisher.class);
    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishPaymentCreatedEvent(CheckoutDTO checkoutDTO) {
        logger.info("Publicando evento de pagamento criado: {}", checkoutDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, "", checkoutDTO);
    }

    public void publishPaymentUpdatedEvent(CheckoutDTO checkoutDTO) {
        logger.info("Publicando evento de pagamento atualizado: {}", checkoutDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, "", checkoutDTO);
    }

    public void publishPaymentDeletedEvent(CheckoutDTO checkoutDTO) {
        logger.info("Publicando evento de pagamento deletado: {}", checkoutDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, "", checkoutDTO);
    }
}
