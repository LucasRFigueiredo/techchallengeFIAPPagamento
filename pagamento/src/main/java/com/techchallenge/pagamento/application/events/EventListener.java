package com.techchallenge.pagamento.application.events;

import com.techchallenge.pagamento.application.dto.PedidoDTO;
import com.techchallenge.pagamento.application.usecases.CheckoutServiceImpl;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    private final CheckoutServiceImpl checkoutService;
    private final PedidoMapper pedidoMapper;

    public EventListener(CheckoutServiceImpl checkoutService, PedidoMapper pedidoMapper) {
        this.checkoutService = checkoutService;
        this.pedidoMapper = pedidoMapper;
    }

    @RabbitListener(queues = "checkoutQueue")
    public void handleCheckout(PedidoDTO pedidoDTO) {
        checkoutService.criar(pedidoMapper.pedidoDTOToPedido(pedidoDTO));
    }
}

