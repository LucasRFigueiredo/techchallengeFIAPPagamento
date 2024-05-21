package com.techchallenge.pagamento.infrastructure.mapper.produto;

import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void testUpdateProdutoEntity() {
        Produto produto = new Produto(3L, "Fast-food", "Hambúrguer com queijo", "Um hambúrguer delicioso com queijo", 12.99);
        ProdutoEntity produtoEntity = new ProdutoEntity();

        ProdutoEntity updatedProdutoEntity = ProdutoEntityMapper.updateProdutoEntity(produto, produtoEntity);

        assertNotNull(updatedProdutoEntity);
        assertEquals("Fast-food", updatedProdutoEntity.getTipo());
        assertEquals("Hambúrguer com queijo", updatedProdutoEntity.getNome());
        assertEquals("Um hambúrguer delicioso com queijo", updatedProdutoEntity.getDescricao());
        assertEquals(12.99, updatedProdutoEntity.getPreco());
    }

    @Test
    public void testUpdateProdutoEntityWithNullFields() {
        Produto produto = new Produto(4L, null, "Batata Frita", null, 6.0);
        ProdutoEntity produtoEntity = new ProdutoEntity(4L, "Acompanhamento", "Antiga Batata", "Batata com sal", 4.0, null);

        ProdutoEntity updatedProdutoEntity = ProdutoEntityMapper.updateProdutoEntity(produto, produtoEntity);

        assertNotNull(updatedProdutoEntity);
        assertEquals("Acompanhamento", updatedProdutoEntity.getTipo());
        assertEquals("Batata Frita", updatedProdutoEntity.getNome());
        assertEquals("Batata com sal", updatedProdutoEntity.getDescricao());
        assertEquals(6.0, updatedProdutoEntity.getPreco());
    }
}
