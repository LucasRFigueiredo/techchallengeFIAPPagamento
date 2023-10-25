package com.techchallenge.lanchonete.Produto.Port.Repository;

import com.techchallenge.lanchonete.Produto.Domain.Entity.Produto;

public interface ProdutoRepositoryPort {
    void salvar(Produto produto);
}
