package com.techchallenge.producao.infrastructure.gateways;

import com.techchallenge.producao.application.gateways.pedido.CriarPedidoUseCase;
import com.techchallenge.producao.application.gateways.pedido.ListarPedidoUseCase;
import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.producao.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.producao.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PedidoRepositoryGateway implements CriarPedidoUseCase, ListarPedidoUseCase {
    private final SpringPedidoRepository springPedidoRepository;
    private final PedidoEntityMapper pedidoEntityMapper;
    private final EntityManager entityManager;

    public PedidoRepositoryGateway(SpringPedidoRepository springPedidoRepository, PedidoEntityMapper pedidoEntityMapper,
                                   EntityManager entityManager) {
        this.springPedidoRepository = springPedidoRepository;
        this.pedidoEntityMapper = pedidoEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    public Long criar(Pedido pedido) {
        PedidoEntity pedidoEntity;
        if (Objects.isNull(pedido.getId())) {
            pedidoEntity = pedidoEntityMapper.pedidoToPedidoEntity(pedido);
        } else {
            pedidoEntity = this.springPedidoRepository.findById(pedido.getId()).get();
            pedidoEntity = pedidoEntityMapper.pedidoToPedidoEntity(pedido);
        }
        ClienteEntity clienteEntity = pedidoEntity.getCliente();
        if (clienteEntity != null && !entityManager.contains(clienteEntity)) {
            clienteEntity = entityManager.merge(clienteEntity);
            pedidoEntity.setCliente(clienteEntity);
        }
        this.springPedidoRepository.save(pedidoEntity);
        return pedidoEntity.getId();
    }

    @Override
    public List<Pedido> listar() {
        List<PedidoEntity> pedidoEntities = this.springPedidoRepository.findAll();
        if (!pedidoEntities.isEmpty()) {
            List<Pedido> pedidos = new ArrayList<>();
            for (PedidoEntity pedidoEntity : pedidoEntities) {
                Pedido pedido = pedidoEntityMapper.pedidoEntityToPedido(pedidoEntity);
                pedidos.add(pedido);
            }
            return pedidos;
        }
        return Collections.emptyList();
    }
}
