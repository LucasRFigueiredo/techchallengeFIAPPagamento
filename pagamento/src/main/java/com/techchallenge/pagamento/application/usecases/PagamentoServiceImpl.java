package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;

public class PagamentoServiceImpl {
    private final CheckoutUseCase checkoutUseCase;

    public PagamentoServiceImpl(CheckoutUseCase checkoutUseCase) {
        this.checkoutUseCase = checkoutUseCase;
    }

    public void aprovar(Long id) {
        Checkout checkout = checkoutUseCase.buscar(id);
        if (checkout != null) {
            checkout.setPagamento("Aprovado");
        }
        checkoutUseCase.atualizarPagamento(checkout);
    }

    public void reprovar(Long id) {
        Checkout checkout = checkoutUseCase.buscar(id);
        if (checkout != null) {
            checkout.setPagamento("Reprovado");
        }
        checkoutUseCase.atualizarPagamento(checkout);
    }
}
