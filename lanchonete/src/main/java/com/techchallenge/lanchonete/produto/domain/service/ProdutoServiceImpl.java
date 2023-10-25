package com.techchallenge.lanchonete.produto.domain.service;

import com.techchallenge.lanchonete.produto.domain.Dto.ProdutoDto;
import com.techchallenge.lanchonete.produto.domain.entity.Produto;
import com.techchallenge.lanchonete.produto.domain.mapper.ProdutoMapper;
import com.techchallenge.lanchonete.produto.port.interfaces.ProdutoServicePort;
import com.techchallenge.lanchonete.produto.port.repository.ProdutoRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoServicePort {
    private final ProdutoRepositoryPort produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Override
    public void criarProduto(ProdutoDto produtoDto) {
        Produto produto = produtoMapper.produtoDTOToProduto(produtoDto);
        this.produtoRepository.salvar(produto);
    }
}
