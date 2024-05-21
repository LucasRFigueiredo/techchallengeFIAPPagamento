package com.techchallenge.producao.application.gateways.pedido;

import com.techchallenge.producao.domain.Pedido;

public interface CriarPedidoUseCase {
    Long criar(Pedido pedido);
}
