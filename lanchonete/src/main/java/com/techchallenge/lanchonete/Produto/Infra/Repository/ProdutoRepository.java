package com.techchallenge.lanchonete.Produto.Infra.Repository;

import com.techchallenge.lanchonete.Produto.Domain.Entity.Produto;
import com.techchallenge.lanchonete.Produto.Port.Repository.ProdutoRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRepository implements ProdutoRepositoryPort {
    @Override
    public void salvar(Produto produto) {

    }
}
