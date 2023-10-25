package com.techchallenge.lanchonete.pedido.port.interfaces;

import com.techchallenge.lanchonete.pedido.domain.dto.PedidoDTO;

public interface PedidoServicePort {
    void criarPedido(PedidoDTO pedidoDTO);
    //TODO implementar metodos de busca de pedido
}
