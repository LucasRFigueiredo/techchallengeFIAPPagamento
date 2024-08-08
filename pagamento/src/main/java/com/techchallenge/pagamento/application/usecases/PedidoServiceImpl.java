package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.dto.PedidoDTO;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl {

    private final SpringPedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final PedidoEntityMapper pedidoEntityMapper;

    public PedidoServiceImpl(SpringPedidoRepository pedidoRepository, PedidoMapper pedidoMapper, PedidoEntityMapper pedidoEntityMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
        this.pedidoEntityMapper = pedidoEntityMapper;
    }

    public void createPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);
        pedidoRepository.save(pedidoEntityMapper.pedidoToPedidoEntity(pedido));
    }

    public void updatePedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);
        pedidoRepository.save(pedidoEntityMapper.pedidoToPedidoEntity(pedido));
    }

    public void deletePedido(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
}
