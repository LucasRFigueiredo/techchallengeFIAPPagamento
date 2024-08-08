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

    public void publishPaymentApprovedEvent(String orderId) {
        logger.info("Publicando evento de pagamento aprovado para pedido: {}", orderId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, RabbitMQConfig.PAYMENT_APPROVED_ROUTING_KEY, orderId);

        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, RabbitMQConfig.NOTIFICATION_QUEUE, "Pagamento aprovado para o pedido: " + orderId);

        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, RabbitMQConfig.KITCHEN_QUEUE, "Novo pedido aprovado: " + orderId);
    }

    public void publishPaymentRejectedEvent(String orderId) {
        logger.info("Publicando evento de pagamento rejeitado para pedido: {}", orderId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, RabbitMQConfig.PAYMENT_REJECTED_ROUTING_KEY, orderId);

        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_EXCHANGE, RabbitMQConfig.NOTIFICATION_QUEUE, "Pagamento rejeitado para o pedido: " + orderId);
    }
}
