package com.techchallenge.lanchonete.pedido.domain.service;

import com.techchallenge.lanchonete.pedido.domain.dto.PedidoDTO;
import com.techchallenge.lanchonete.pedido.domain.entity.Pedido;
import com.techchallenge.lanchonete.pedido.domain.mapper.PedidoMapper;
import com.techchallenge.lanchonete.pedido.port.interfaces.PedidoServicePort;
import com.techchallenge.lanchonete.pedido.port.repository.PedidoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PedidoServiceImpl implements PedidoServicePort {
    private final PedidoRepositoryPort pedidoRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    public void criarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);
        this.pedidoRepository.salvar(pedido);
    }
}
