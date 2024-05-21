package com.techchallenge.pagamento.infrastructure.mapper.produto;

import com.techchallenge.pagamento.application.dto.ProdutoDTO;
import com.techchallenge.pagamento.domain.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto produtoDTOToProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.getId());
        produto.setTipo(produtoDTO.getTipo());
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        return produto;
    }

    public ProdutoDTO produtoToProdutoDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setTipo(produto.getTipo());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());
        return produtoDTO;
    }
}

