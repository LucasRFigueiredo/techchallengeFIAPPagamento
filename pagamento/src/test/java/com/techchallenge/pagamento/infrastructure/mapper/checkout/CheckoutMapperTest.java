package com.techchallenge.pagamento.infrastructure.mapper.checkout;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckoutMapperTest {

    @Test
    public void testCheckoutToCheckoutDTO() {
        Checkout checkout = new Checkout();
        checkout.setId(1L);
        checkout.setTotal(new BigDecimal("10.00"));
        checkout.setPagamento("Cartão");
        checkout.setStatus("Pendente");

        PedidoMapper pedidoMapper = mock(PedidoMapper.class);
        when(pedidoMapper.pedidoToPedidoDTO(null)).thenReturn(null);

        CheckoutMapper mapper = new CheckoutMapper(pedidoMapper);

        CheckoutDTO checkoutDTO = mapper.checkoutToCheckoutDTO(checkout);

        assertEquals(checkout.getId(), checkoutDTO.getId());
        assertEquals(checkout.getTotal(), checkoutDTO.getTotal());
        assertEquals(checkout.getPagamento(), checkoutDTO.getPagamento());
        assertEquals(checkout.getStatus(), checkoutDTO.getStatus());
    }
}
