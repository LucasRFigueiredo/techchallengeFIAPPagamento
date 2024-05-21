package com.techchallenge.pagamento.infrastructure.mapper.pedido;

import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoEntityMapper {

    private final ClienteEntityMapper clienteEntityMapper;
    private final ProdutoEntityMapper produtoEntityMapper;

    public PedidoEntityMapper(ClienteEntityMapper clienteEntityMapper, ProdutoEntityMapper produtoEntityMapper) {
        this.clienteEntityMapper = clienteEntityMapper;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    public Pedido pedidoEntityToPedido(PedidoEntity pedidoEntity) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoEntity.getId());
        pedido.setCliente(clienteEntityMapper.clienteEntityToCliente(pedidoEntity.getCliente()));
        pedido.setStatus(pedidoEntity.getStatus());

        List<Produto> produtos = new ArrayList<>();
        for (ProdutoEntity produtoEntity : pedidoEntity.getProdutos()) {
            produtos.add(produtoEntityMapper.produtoEntityToProduto(produtoEntity));
        }
        pedido.setItens(produtos);

        return pedido;
    }

    public PedidoEntity pedidoToPedidoEntity(Pedido pedido) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(pedido.getId());
        pedidoEntity.setCliente(clienteEntityMapper.clienteToClienteEntity(pedido.getCliente()));
        pedidoEntity.setStatus(pedido.getStatus());

        List<ProdutoEntity> produtos = new ArrayList<>();
        for (Produto produto : pedido.getItens()) {
            produtos.add(produtoEntityMapper.produtoToProdutoEntity(produto));
        }
        pedidoEntity.setProdutos(produtos);

        return pedidoEntity;
    }
}
