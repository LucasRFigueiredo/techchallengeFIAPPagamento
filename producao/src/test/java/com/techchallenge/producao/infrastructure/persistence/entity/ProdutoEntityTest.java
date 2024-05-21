package com.techchallenge.producao.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ProdutoEntityTest {

    @Test
    public void testProdutoEntity() {
        List<PedidoEntity> pedidos = new ArrayList<>();

        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(1L);
        pedidos.add(pedido);
        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);
        produto.setTipo("Alimento");
        produto.setNome("Hambúrguer");
        produto.setDescricao("Delicioso hambúrguer caseiro");
        produto.setPreco(15.0);
        produto.setPedidos(pedidos);
        assertEquals(1L, produto.getId());
        assertEquals("Alimento", produto.getTipo());
        assertEquals("Hambúrguer", produto.getNome());
        assertEquals("Delicioso hambúrguer caseiro", produto.getDescricao());
        assertEquals(15.0, produto.getPreco());
        assertEquals(1, produto.getPedidos().size());
        assertTrue(produto.getPedidos().contains(pedido));
        assertEquals(pedido, produto.getPedidos().get(0));
    }
}
