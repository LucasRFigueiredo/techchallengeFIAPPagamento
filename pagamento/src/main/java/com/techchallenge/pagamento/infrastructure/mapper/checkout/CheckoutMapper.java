package com.techchallenge.pagamento.infrastructure.mapper.checkout;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoMapper;
import org.springframework.stereotype.Component;

@Component
public class CheckoutMapper {
    private final PedidoMapper pedidoMapper;

    public CheckoutMapper(PedidoMapper pedidoMapper) {
        this.pedidoMapper = pedidoMapper;
    }

    public CheckoutDTO checkoutToCheckoutDTO(Checkout checkout) {
        CheckoutDTO checkoutDTO = new CheckoutDTO();
        checkoutDTO.setId(checkout.getId());
        checkoutDTO.setPedido(pedidoMapper.pedidoToPedidoDTO(checkout.getPedido()));
        checkoutDTO.setTotal(checkout.getTotal());
        checkoutDTO.setPagamento(checkout.getPagamento());
        checkoutDTO.setStatus(checkout.getStatus());
        return checkoutDTO;
    }
}
