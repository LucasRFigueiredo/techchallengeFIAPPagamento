package com.techchallenge.lanchonete.Pedido.Domain.Service;

import com.techchallenge.lanchonete.Pedido.Domain.Dto.PedidoDTO;
import com.techchallenge.lanchonete.Pedido.Domain.Entity.Pedido;
import com.techchallenge.lanchonete.Pedido.Domain.Mapper.PedidoMapper;
import com.techchallenge.lanchonete.Pedido.Port.Interface.PedidoServicePort;
import com.techchallenge.lanchonete.Pedido.Port.Repository.PedidoRepositoryPort;
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
