package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutServiceImpl {
    private final CheckoutUseCase checkoutUseCase;
    private final CheckoutMapper checkoutMapper;

    public CheckoutServiceImpl(CheckoutUseCase checkoutUseCase, CheckoutMapper checkoutMapper) {
        this.checkoutUseCase = checkoutUseCase;
        this.checkoutMapper = checkoutMapper;
    }

    @Transactional
    public void criar(Pedido pedido) {
        BigDecimal total = new BigDecimal(0);
        for (Produto produto : pedido.getItens()) {
            total = total.add(BigDecimal.valueOf(produto.getPreco()));
        }

        // Salvar pedido antes de criar o checkout
        Pedido savedPedido = checkoutUseCase.salvarPedido(pedido);

        Checkout checkout = new Checkout();
        checkout.setPedido(savedPedido);
        checkout.setTotal(total);
        checkout.setPagamento("Aguardando pagamento");
        checkout.setStatus("Aguardando pagamento");

        checkoutUseCase.criar(checkout);
    }

    public List<CheckoutDTO> buscar() {
        List<Checkout> checkouts = checkoutUseCase.listar();
        if (!checkouts.isEmpty()) {
            List<CheckoutDTO> checkoutDTOS = new ArrayList<>();
            for (Checkout checkout : checkouts) {
                checkoutDTOS.add(checkoutMapper.checkoutToCheckoutDTO(checkout));
            }
            return checkoutDTOS;
        }
        return Collections.emptyList();
    }
}
