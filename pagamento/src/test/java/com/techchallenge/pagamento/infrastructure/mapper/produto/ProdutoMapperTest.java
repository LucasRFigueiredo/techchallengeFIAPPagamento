package com.techchallenge.pagamento.infrastructure.mapper.produto;

import com.techchallenge.pagamento.application.dto.ProdutoDTO;
import com.techchallenge.pagamento.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoMapperTest {

    private ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        produtoMapper = new ProdutoMapper();
    }

    @Test
    void shouldTestIfProdutoDTOToProdutoMapperIsWorking() {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setTipo("Fast-food");
        produtoDTO.setNome("Hambúrguer com queijo");
        produtoDTO.setDescricao("Um hambúrguer delicioso com queijo");
        produtoDTO.setPreco(12.99);

        Produto produto = produtoMapper.produtoDTOToProduto(produtoDTO);

        assertEquals(produtoDTO.getId(), produto.getId());
        assertEquals(produtoDTO.getTipo(), produto.getTipo());
        assertEquals(produtoDTO.getNome(), produto.getNome());
        assertEquals(produtoDTO.getDescricao(), produto.getDescricao());
        assertEquals(produtoDTO.getPreco(), produto.getPreco());
    }

    @Test
    void shouldTestIfProdutoToProdutoDTOMapperIsWorking() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setTipo("Fast-food");
        produto.setNome("Hambúrguer com queijo");
        produto.setDescricao("Um hambúrguer delicioso com queijo");
        produto.setPreco(12.99);

        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);

        assertEquals(produto.getId(), produtoDTO.getId());
        assertEquals(produto.getTipo(), produtoDTO.getTipo());
        assertEquals(produto.getNome(), produtoDTO.getNome());
        assertEquals(produto.getDescricao(), produtoDTO.getDescricao());
        assertEquals(produto.getPreco(), produtoDTO.getPreco());
    }
}
