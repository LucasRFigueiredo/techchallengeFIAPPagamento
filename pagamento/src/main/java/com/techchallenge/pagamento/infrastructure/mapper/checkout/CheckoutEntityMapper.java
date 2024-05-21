package com.techchallenge.pagamento.infrastructure.mapper.checkout;

import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CheckoutEntityMapper {
    private final PedidoEntityMapper pedidoEntityMapper;

    public CheckoutEntityMapper(PedidoEntityMapper pedidoEntityMapper) {
        this.pedidoEntityMapper = pedidoEntityMapper;
    }

    public CheckoutEntity checkoutToCheckoutEntity(Checkout checkout) {
        CheckoutEntity checkoutEntity = new CheckoutEntity();
        checkoutEntity.setPedido(pedidoEntityMapper.pedidoToPedidoEntity(checkout.getPedido()));
        checkoutEntity.setTotal(checkout.getTotal());
        checkoutEntity.setId(checkout.getId());
        checkoutEntity.setPagamento(checkout.getPagamento());
        checkoutEntity.setStatus(checkout.getStatus());
        return checkoutEntity;
    }

    public Checkout checkoutEntityToCheckout(CheckoutEntity checkoutEntity) {
        Checkout checkout = new Checkout();
        checkout.setId(checkoutEntity.getId());
        checkout.setPedido(pedidoEntityMapper.pedidoEntityToPedido(checkoutEntity.getPedido()));
        checkout.setTotal(checkoutEntity.getTotal());
        checkout.setId(checkoutEntity.getId());
        checkout.setPagamento(checkoutEntity.getPagamento());
        checkout.setStatus(checkoutEntity.getStatus());
        return checkout;
    }

    public static CheckoutEntity updateCheckoutEntityPagamento(Checkout checkout, CheckoutEntity checkoutEntity) {
        if (!Objects.isNull(checkout.getPagamento())) {
            checkoutEntity.setPagamento(checkout.getPagamento());
        }
        return checkoutEntity;
    }

    public CheckoutEntity updateCheckoutEntityStatus(Checkout checkout, CheckoutEntity checkoutEntity) {
        if (!Objects.isNull(checkout.getStatus())) {
            checkoutEntity.setStatus(checkout.getStatus());
        }
        return checkoutEntity;
    }

    public List<Checkout> checkoutEntitiesToCheckouts(List<CheckoutEntity> checkoutEntities) {
        if (!checkoutEntities.isEmpty()) {
            List<Checkout> checkouts = new ArrayList<>();
            for (CheckoutEntity checkoutEntity : checkoutEntities) {
                Checkout checkout = new Checkout();
                checkout.setId(checkoutEntity.getId());
                checkout.setPedido(pedidoEntityMapper.pedidoEntityToPedido(checkoutEntity.getPedido()));
                checkout.setTotal(checkoutEntity.getTotal());
                checkout.setPagamento(checkoutEntity.getPagamento());
                checkout.setStatus(checkoutEntity.getStatus());
                checkouts.add(checkout);
            }
            return checkouts;
        }
        return null;
    }
}
