package com.techchallenge.pagamento.infrastructure.mapper.pedido;

import com.techchallenge.pagamento.domain.Cliente;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PedidoEntityMapperTest {

    private PedidoEntityMapper pedidoEntityMapper;
    private ClienteEntityMapper clienteEntityMapper;
    private ProdutoEntityMapper produtoEntityMapper;

    @BeforeEach
    public void setUp() {
        clienteEntityMapper = mock(ClienteEntityMapper.class);
        produtoEntityMapper = mock(ProdutoEntityMapper.class);
        pedidoEntityMapper = new PedidoEntityMapper(clienteEntityMapper, produtoEntityMapper);
    }

    @Test
    public void testPedidoEntityToPedido() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId("664c22ed062528092ab45298");
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(1L);
        pedidoEntity.setCliente(clienteEntity);
        pedidoEntity.setStatus("Pendente");
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        List<ProdutoEntity> produtos = new ArrayList<>();
        produtos.add(produtoEntity);
        pedidoEntity.setProdutos(produtos);

        when(clienteEntityMapper.clienteEntityToCliente(clienteEntity)).thenReturn(new Cliente("664c22ed062528092ab45298", "Fulano da silva", "12345678901", "fulano@email.com"));
        when(produtoEntityMapper.produtoEntityToProduto(produtoEntity)).thenReturn(new Produto());

        Pedido pedido = pedidoEntityMapper.pedidoEntityToPedido(pedidoEntity);

        assertEquals(1L, pedido.getId());
        assertEquals("664c22ed062528092ab45298", pedido.getCliente().getId());
        assertEquals("Pendente", pedido.getStatus());
        assertEquals(1, pedido.getItens().size());
    }

    @Test
    public void testPedidoToPedidoEntity() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        Cliente cliente = new Cliente();
        cliente.setId("664c22ed062528092ab45298");
        pedido.setCliente(cliente);
        pedido.setStatus("Pendente");
        Produto produto = new Produto();
        produto.setId(1L);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        pedido.setItens(produtos);

        when(clienteEntityMapper.clienteToClienteEntity(cliente)).thenReturn(new ClienteEntity("664c22ed062528092ab45298", "Fulano da silva", "12345678901", "fulano@email.com", List.of(new PedidoEntity())));
        when(produtoEntityMapper.produtoToProdutoEntity(produto)).thenReturn(new ProdutoEntity());

        PedidoEntity pedidoEntity = pedidoEntityMapper.pedidoToPedidoEntity(pedido);

        assertEquals(1L, pedidoEntity.getId());
        assertEquals("664c22ed062528092ab45298", pedidoEntity.getCliente().getId());
        assertEquals("Pendente", pedidoEntity.getStatus());
        assertEquals(1, pedidoEntity.getProdutos().size());
    }
}
