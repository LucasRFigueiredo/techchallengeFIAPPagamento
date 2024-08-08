package com.techchallenge.producao.application.events;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.configuration.RabbitMQConfig;
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

    public void publishProductCreatedEvent(ProdutoDTO produtoDTO) {
        logger.info("Publicando evento de produto criado: {}", produtoDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PRODUCT_EXCHANGE, "product.created", produtoDTO);
    }

    public void publishProductUpdatedEvent(ProdutoDTO produtoDTO) {
        logger.info("Publicando evento de produto atualizado: {}", produtoDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PRODUCT_EXCHANGE, "product.updated", produtoDTO);
    }

    public void publishProductDeletedEvent(ProdutoDTO produtoDTO) {
        logger.info("Publicando evento de produto deletado: {}", produtoDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PRODUCT_EXCHANGE, "product.deleted", produtoDTO);
    }

    public void publishOrderCreatedEvent(PedidoDTO pedidoDTO) {
        logger.info("Publicando evento de pedido criado: {}", pedidoDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, "order.created", pedidoDTO);
    }

    public void publishOrderUpdatedEvent(PedidoDTO pedidoDTO) {
        logger.info("Publicando evento de pedido atualizado: {}", pedidoDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, "order.updated", pedidoDTO);
    }

    public void publishOrderDeletedEvent(PedidoDTO pedidoDTO) {
        logger.info("Publicando evento de pedido deletado: {}", pedidoDTO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, "order.deleted", pedidoDTO);
    }
}
