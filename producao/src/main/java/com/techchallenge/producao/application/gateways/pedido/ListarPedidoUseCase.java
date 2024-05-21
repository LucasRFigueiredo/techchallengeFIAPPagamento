package com.techchallenge.producao.application.gateways.pedido;

import com.techchallenge.producao.domain.Pedido;

import java.util.List;

public interface ListarPedidoUseCase {

    List<Pedido> listar();
}
