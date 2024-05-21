package com.techchallenge.producao.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PedidoEntityTest {

    @Test
    public void testPedidoEntity() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId("664c22ed062528092ab45298");
        cliente.setNome("Cliente Teste");

        ProdutoEntity produto1 = new ProdutoEntity();
        produto1.setId(1L);
        produto1.setNome("Produto 1");

        ProdutoEntity produto2 = new ProdutoEntity();
        produto2.setId(2L);
        produto2.setNome("Produto 2");

        List<ProdutoEntity> produtos = Arrays.asList(produto1, produto2);

        CheckoutEntity checkout = new CheckoutEntity();
        checkout.setId(1L);

        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setStatus("Em andamento");
        pedido.setProdutos(produtos);
        pedido.setCheckout(checkout);

        assertEquals(1L, pedido.getId());
        assertEquals(cliente, pedido.getCliente());
        assertEquals("Em andamento", pedido.getStatus());
        assertEquals(produtos, pedido.getProdutos());
        assertEquals(checkout, pedido.getCheckout());
    }
}
