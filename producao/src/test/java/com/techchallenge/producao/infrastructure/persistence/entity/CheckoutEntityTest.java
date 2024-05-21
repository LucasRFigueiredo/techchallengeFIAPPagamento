package com.techchallenge.producao.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutEntityTest {

    @Test
    public void testCheckoutEntity() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setId(1L);
        CheckoutEntity checkout = new CheckoutEntity();
        checkout.setId(1L);
        checkout.setPedido(pedido);
        checkout.setTotal(BigDecimal.valueOf(100.0));
        checkout.setPagamento("Cartão de Crédito");
        checkout.setStatus("Confirmado");
        assertEquals(1L, checkout.getId());
        assertEquals(pedido, checkout.getPedido());
        assertEquals(BigDecimal.valueOf(100.0), checkout.getTotal());
        assertEquals("Cartão de Crédito", checkout.getPagamento());
        assertEquals("Confirmado", checkout.getStatus());
    }
}
