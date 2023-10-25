package com.techchallenge.lanchonete.Produto.Domain.Service;

import com.techchallenge.lanchonete.Produto.Domain.Dto.ProdutoDto;
import com.techchallenge.lanchonete.Produto.Domain.Entity.Produto;
import com.techchallenge.lanchonete.Produto.Domain.Mapper.ProdutoMapper;
import com.techchallenge.lanchonete.Produto.Port.Interface.ProdutoServicePort;
import com.techchallenge.lanchonete.Produto.Port.Repository.ProdutoRepositoryPort;
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
