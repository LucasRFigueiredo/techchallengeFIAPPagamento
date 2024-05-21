package com.techchallenge.pagamento.infrastructure.mapper.checkout;

import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckoutEntityMapperTest {

    @Test
    public void testCheckoutToCheckoutEntity() {
        Checkout checkout = new Checkout();
        checkout.setId(1L);
        checkout.setTotal(new BigDecimal("10.00"));
        checkout.setPagamento("Cartão");
        checkout.setStatus("Pendente");

        PedidoEntityMapper pedidoEntityMapper = mock(PedidoEntityMapper.class);
        when(pedidoEntityMapper.pedidoToPedidoEntity(checkout.getPedido())).thenReturn(null);

        CheckoutEntityMapper mapper = new CheckoutEntityMapper(pedidoEntityMapper);

        CheckoutEntity checkoutEntity = mapper.checkoutToCheckoutEntity(checkout);

        assertEquals(checkout.getId(), checkoutEntity.getId());
        assertEquals(checkout.getTotal(), checkoutEntity.getTotal());
        assertEquals(checkout.getPagamento(), checkoutEntity.getPagamento());
        assertEquals(checkout.getStatus(), checkoutEntity.getStatus());
        assertNull(checkoutEntity.getPedido());
    }

    @Test
    public void testCheckoutEntityToCheckout() {
        CheckoutEntity checkoutEntity = new CheckoutEntity();
        checkoutEntity.setId(1L);
        checkoutEntity.setTotal(new BigDecimal("10.00"));
        checkoutEntity.setPagamento("Cartão");
        checkoutEntity.setStatus("Pendente");

        PedidoEntityMapper pedidoEntityMapper = mock(PedidoEntityMapper.class);
        when(pedidoEntityMapper.pedidoEntityToPedido(null)).thenReturn(null);

        CheckoutEntityMapper mapper = new CheckoutEntityMapper(pedidoEntityMapper);

        Checkout checkout = mapper.checkoutEntityToCheckout(checkoutEntity);

        assertEquals(checkoutEntity.getId(), checkout.getId());
        assertEquals(checkoutEntity.getTotal(), checkout.getTotal());
        assertEquals(checkoutEntity.getPagamento(), checkout.getPagamento());
        assertEquals(checkoutEntity.getStatus(), checkout.getStatus());
        assertNull(checkout.getPedido());
    }

    @Test
    public void testUpdateCheckoutEntityPagamento() {
        Checkout checkout = new Checkout();
        checkout.setId(1L);
        checkout.setPagamento("Dinheiro");

        CheckoutEntity checkoutEntity = new CheckoutEntity();
        checkoutEntity.setId(1L);
        checkoutEntity.setPagamento("Cartão");

        CheckoutEntityMapper mapper = new CheckoutEntityMapper(null);

        CheckoutEntity updatedEntity = CheckoutEntityMapper.updateCheckoutEntityPagamento(checkout, checkoutEntity);

        assertEquals(checkout.getPagamento(), updatedEntity.getPagamento());
    }
}
