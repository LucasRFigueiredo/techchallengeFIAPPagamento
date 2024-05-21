package com.techchallenge.pagamento.application.gateways.checkout;

import com.techchallenge.pagamento.domain.Checkout;

import java.util.List;

public interface CheckoutUseCase {
    void criar(Checkout checkout);

    List<Checkout> listar();

    Checkout buscar(Long id);

    void atualizarPagamento(Checkout checkout);

    void atualizarStatus(Checkout checkout);

    List<Checkout> buscarPorStatusPagamento(String status);
}
