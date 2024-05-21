package com.techchallenge.producao.application.gateways.produto;

import com.techchallenge.producao.domain.Produto;

public interface EditarProdutoUseCase {

    void editar(Produto produto, Long id);
}
