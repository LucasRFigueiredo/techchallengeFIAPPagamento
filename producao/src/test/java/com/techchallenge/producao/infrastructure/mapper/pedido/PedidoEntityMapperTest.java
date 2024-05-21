package com.techchallenge.producao.infrastructure.mapper.pedido;

import com.techchallenge.producao.domain.Cliente;
import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.producao.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.producao.infrastructure.persistence.entity.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoEntityMapperTest {

    private PedidoEntityMapper pedidoEntityMapper;
    private ClienteEntityMapper clienteEntityMapper;
    private ProdutoEntityMapper produtoEntityMapper;

    @BeforeEach
    void setUp() {
        clienteEntityMapper = new ClienteEntityMapper();
        produtoEntityMapper = new ProdutoEntityMapper();
        pedidoEntityMapper = new PedidoEntityMapper(clienteEntityMapper, produtoEntityMapper);
    }

    @Test
    void pedidoEntityToPedido() {
        // Criação de um PedidoEntity para teste
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(1L);
        pedidoEntity.setStatus("Entregue");
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId("664c22ed062528092ab45298");
        pedidoEntity.setCliente(clienteEntity);
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setNome("Hambúrguer");
        produtoEntity.setDescricao("Hambúrguer delicioso");
        produtoEntities.add(produtoEntity);
        pedidoEntity.setProdutos(produtoEntities);

        Pedido pedido = pedidoEntityMapper.pedidoEntityToPedido(pedidoEntity);

        assertEquals(pedidoEntity.getId(), pedido.getId());
        assertEquals(pedidoEntity.getStatus(), pedido.getStatus());
        assertEquals(pedidoEntity.getCliente().getId(), pedido.getCliente().getId());
        assertEquals(pedidoEntity.getProdutos().size(), pedido.getItens().size());
    }

    @Test
    void pedidoToPedidoEntity() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus("Entregue");
        Cliente cliente = new Cliente();
        cliente.setId("664c22ed062528092ab45298");
        pedido.setCliente(cliente);
        List<Produto> produtos = new ArrayList<>();
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Hambúrguer");
        produto.setDescricao("Hambúrguer delicioso");
        produtos.add(produto);
        pedido.setItens(produtos);
        PedidoEntity pedidoEntity = pedidoEntityMapper.pedidoToPedidoEntity(pedido);

        assertEquals(pedido.getId(), pedidoEntity.getId());
        assertEquals(pedido.getStatus(), pedidoEntity.getStatus());
        assertEquals(pedido.getCliente().getId(), pedidoEntity.getCliente().getId());
        assertEquals(pedido.getItens().size(), pedidoEntity.getProdutos().size());
    }
}
