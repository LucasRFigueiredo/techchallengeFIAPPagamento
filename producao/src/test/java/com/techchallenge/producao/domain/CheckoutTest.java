package com.techchallenge.producao.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    @Test
    public void testCheckout() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        Checkout checkout = new Checkout();
        checkout.setId(1L);
        checkout.setPedido(pedido);
        checkout.setTotal(BigDecimal.valueOf(100.0));
        checkout.setPagamento("Aprovado");
        checkout.setStatus("Em preparação");
        assertEquals(1L, checkout.getId());
        assertEquals(pedido, checkout.getPedido());
        assertEquals(BigDecimal.valueOf(100.0), checkout.getTotal());
        assertEquals("Aprovado", checkout.getPagamento());
        assertEquals("Em preparação", checkout.getStatus());
    }
}
