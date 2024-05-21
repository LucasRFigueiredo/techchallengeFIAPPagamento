package com.techchallenge.producao.application.usecases;

import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.application.gateways.produto.*;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProdutoServiceImpl {
    private final CriarProdutoUseCase criarProdutoUseCase;
    private final BuscarTipoProdutoUseCase buscarTipoProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final EditarProdutoUseCase editarProdutoUseCase;
    private final RemoverProdutoUseCase removerProdutoUseCase;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImpl(CriarProdutoUseCase criarProdutoUseCase, BuscarTipoProdutoUseCase buscarTipoProdutoUseCase,
                              BuscarProdutoUseCase buscarProdutoUseCase, EditarProdutoUseCase editarProdutoUseCase,
                              RemoverProdutoUseCase removerProdutoUseCase,
                              ProdutoMapper produtoMapper) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.buscarTipoProdutoUseCase = buscarTipoProdutoUseCase;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.editarProdutoUseCase = editarProdutoUseCase;
        this.removerProdutoUseCase = removerProdutoUseCase;
        this.produtoMapper = produtoMapper;
    }


    public void criar(Produto produto) {
        criarProdutoUseCase.criar(produto);
    }


    public List<ProdutoDTO> buscarTipo(String tipo) {
        List<Produto> produtos = this.buscarTipoProdutoUseCase.buscarTipo(tipo);
        if (!produtos.isEmpty()) {
            List<ProdutoDTO> produtoDTOS = new ArrayList<>();
            for (Produto produto : produtos) {
                ProdutoDTO produtoDTO = new ProdutoDTO();
                produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
                produtoDTOS.add(produtoDTO);
            }
            return produtoDTOS;
        }
        return Collections.emptyList();
    }


    public void editar(Produto produto, Long id) {
        this.editarProdutoUseCase.editar(produto, id);
    }


    public void remover(Long id) {
        this.removerProdutoUseCase.remover(id);
    }


}
