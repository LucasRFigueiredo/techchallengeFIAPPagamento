package com.techchallenge.pagamento.application.gateways.checkout;

import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.domain.Cliente;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;

import java.util.List;

public interface CheckoutUseCase {
    void criar(Checkout checkout);

    List<Checkout> listar();

    List<Checkout> buscarPorStatusPagamento(String statusPagamento);

    Pedido salvarPedido(Pedido pedido);

    Cliente salvarCliente(Cliente cliente);

    Produto salvarProduto(Produto produto);

    void atualizarStatus(Checkout checkout);

    Checkout buscar(Long id);
}

