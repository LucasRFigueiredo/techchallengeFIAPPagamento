package com.techchallenge.lanchonete.produto.port.repository;

import com.techchallenge.lanchonete.produto.domain.entity.Produto;

public interface ProdutoRepositoryPort {
    void salvar(Produto produto);
}
