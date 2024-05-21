package com.techchallenge.producao.application.gateways.produto;

import com.techchallenge.producao.domain.Produto;

import java.util.List;

public interface BuscarTipoProdutoUseCase {
    List<Produto> buscarTipo(String tipo);
}
