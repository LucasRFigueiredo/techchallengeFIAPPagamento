package com.techchallenge.producao.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    public void testPedido() {
        Cliente cliente = new Cliente();
        cliente.setId("664c22ed062528092ab45298");
        List<Produto> itens = new ArrayList<>();
        Produto produto1 = new Produto();
        produto1.setId(1L);
        Produto produto2 = new Produto();
        produto2.setId(2L);
        itens.add(produto1);
        itens.add(produto2);
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setStatus("Em andamento");
        pedido.setItens(itens);
        assertEquals(1L, pedido.getId());
        assertEquals(cliente, pedido.getCliente());
        assertEquals("Em andamento", pedido.getStatus());
        assertEquals(2, pedido.getItens().size());
        assertEquals(produto1, pedido.getItens().get(0));
        assertEquals(produto2, pedido.getItens().get(1));
    }

    @Test
    public void testEqualsAndHashCode() {
        Pedido pedido1 = new Pedido(1L, new Cliente(), "Em andamento", null);
        Pedido pedido2 = pedido1;
        assertEquals(pedido1, pedido2);
        assertEquals(pedido1.getId(), pedido2.getId());
        assertEquals(pedido1.hashCode(), pedido2.hashCode());
    }
}
