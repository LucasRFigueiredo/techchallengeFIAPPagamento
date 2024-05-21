package com.techchallenge.producao.application.gateways.produto;

import com.techchallenge.producao.domain.Produto;

public interface BuscarProdutoUseCase {
    Produto buscar(Long id);
}
