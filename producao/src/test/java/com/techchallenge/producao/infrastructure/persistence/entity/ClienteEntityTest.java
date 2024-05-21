package com.techchallenge.producao.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteEntityTest {

    @Test
    public void testClienteEntity() {
        List<PedidoEntity> pedidos = new ArrayList<>();
        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(1L);
        pedidos.add(pedido);
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId("664c22ed062528092ab45298");
        cliente.setNome("Fulano da Silva");
        cliente.setCpf("12345678901");
        cliente.setEmail("fulano@email.com");
        cliente.setPedidos(pedidos);
        assertEquals("664c22ed062528092ab45298", cliente.getId());
        assertEquals("Fulano da Silva", cliente.getNome());
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("fulano@email.com", cliente.getEmail());
        assertEquals(1, cliente.getPedidos().size());
        assertTrue(cliente.getPedidos().contains(pedido));
        assertEquals(pedido, cliente.getPedidos().get(0));
    }
}
