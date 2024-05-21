package com.techchallenge.producao.infrastructure.mapper.produto;

import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoEntityMapperTest {

    private ProdutoEntityMapper produtoEntityMapper;

    @BeforeEach
    void setUp() {
        produtoEntityMapper = new ProdutoEntityMapper();
    }

    @Test
    void produtoEntityToProduto() {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setTipo("Fast-food");
        produtoEntity.setNome("Hambúrguer com queijo");
        produtoEntity.setDescricao("Um hambúrguer delicioso com queijo");
        produtoEntity.setPreco(12.99);

        Produto produto = produtoEntityMapper.produtoEntityToProduto(produtoEntity);

        assertEquals(produtoEntity.getId(), produto.getId());
        assertEquals(produtoEntity.getTipo(), produto.getTipo());
        assertEquals(produtoEntity.getNome(), produto.getNome());
        assertEquals(produtoEntity.getDescricao(), produto.getDescricao());
        assertEquals(produtoEntity.getPreco(), produto.getPreco());
    }

    @Test
    void produtoToProdutoEntity() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setTipo("Fast-food");
        produto.setNome("Hambúrguer com queijo");
        produto.setDescricao("Um hambúrguer delicioso com queijo");
        produto.setPreco(12.99);

        ProdutoEntity produtoEntity = produtoEntityMapper.produtoToProdutoEntity(produto);

        assertEquals(produto.getId(), produtoEntity.getId());
        assertEquals(produto.getTipo(), produtoEntity.getTipo());
        assertEquals(produto.getNome(), produtoEntity.getNome());
        assertEquals(produto.getDescricao(), produtoEntity.getDescricao());
        assertEquals(produto.getPreco(), produtoEntity.getPreco());
    }
}
