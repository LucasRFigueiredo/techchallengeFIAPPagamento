package com.techchallenge.producao.application.events;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.configuration.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_CREATED_QUEUE)
    public void handleProductCreatedEvent(ProdutoDTO produtoDTO) {
        logger.info("Produto criado: {}", produtoDTO);
    }

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_UPDATED_QUEUE)
    public void handleProductUpdatedEvent(ProdutoDTO produtoDTO) {
        logger.info("Produto atualizado: {}", produtoDTO);
    }

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_DELETED_QUEUE)
    public void handleProductDeletedEvent(ProdutoDTO produtoDTO) {
        logger.info("Produto deletado: {}", produtoDTO);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
    public void handleOrderCreatedEvent(PedidoDTO pedidoDTO) {
        logger.info("Pedido criado: {}", pedidoDTO);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_UPDATED_QUEUE)
    public void handleOrderUpdatedEvent(PedidoDTO pedidoDTO) {
        logger.info("Pedido atualizado: {}", pedidoDTO);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_DELETED_QUEUE)
    public void handleOrderDeletedEvent(PedidoDTO pedidoDTO) {
        logger.info("Pedido deletado: {}", pedidoDTO);
    }
}
