package com.techchallenge.producao.infrastructure.mapper.produto;

import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProdutoEntityMapper {
    public Produto produtoEntityToProduto(ProdutoEntity produtoEntity) {
        Produto produto = new Produto();
        produto.setId(produtoEntity.getId());
        produto.setTipo(produtoEntity.getTipo());
        produto.setNome(produtoEntity.getNome());
        produto.setDescricao(produtoEntity.getDescricao());
        produto.setPreco(produtoEntity.getPreco());
        return produto;
    }

    public ProdutoEntity produtoToProdutoEntity(Produto produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(produto.getId());
        produtoEntity.setTipo(produto.getTipo());
        produtoEntity.setNome(produto.getNome());
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setPreco(produto.getPreco());
        return produtoEntity;
    }

    public static ProdutoEntity updateProdutoEntity(Produto produto, ProdutoEntity produtoEntity) {
        if (!Objects.isNull(produto.getTipo())) {
            produtoEntity.setTipo(produto.getTipo());
        }
        if (!Objects.isNull(produto.getNome())) {
            produtoEntity.setNome(produto.getNome());
        }
        if (!Objects.isNull(produto.getDescricao())) {
            produtoEntity.setDescricao(produto.getDescricao());
        }
        if (!Objects.isNull(produto.getPreco())) {
            produtoEntity.setPreco(produto.getPreco());
        }
        return produtoEntity;
    }
}

