package com.techchallenge.producao.application.usecases;

import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.application.gateways.produto.*;
import com.techchallenge.producao.application.events.EventPublisher;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProdutoServiceImpl {
    private final CriarProdutoUseCase criarProdutoUseCase;
    private final BuscarTipoProdutoUseCase buscarTipoProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final EditarProdutoUseCase editarProdutoUseCase;
    private final RemoverProdutoUseCase removerProdutoUseCase;
    private final ProdutoMapper produtoMapper;
    private final EventPublisher eventPublisher;

    public ProdutoServiceImpl(CriarProdutoUseCase criarProdutoUseCase, BuscarTipoProdutoUseCase buscarTipoProdutoUseCase,
                              BuscarProdutoUseCase buscarProdutoUseCase, EditarProdutoUseCase editarProdutoUseCase,
                              RemoverProdutoUseCase removerProdutoUseCase, ProdutoMapper produtoMapper,
                              EventPublisher eventPublisher) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.buscarTipoProdutoUseCase = buscarTipoProdutoUseCase;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.editarProdutoUseCase = editarProdutoUseCase;
        this.removerProdutoUseCase = removerProdutoUseCase;
        this.produtoMapper = produtoMapper;
        this.eventPublisher = eventPublisher;
    }

    public void criar(Produto produto) {
        criarProdutoUseCase.criar(produto);
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
        eventPublisher.publishProductCreatedEvent(produtoDTO);
    }

    public List<ProdutoDTO> buscarTipo(String tipo) {
        List<Produto> produtos = buscarTipoProdutoUseCase.buscarTipo(tipo);
        if (!produtos.isEmpty()) {
            List<ProdutoDTO> produtoDTOS = new ArrayList<>();
            for (Produto produto : produtos) {
                ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
                produtoDTOS.add(produtoDTO);
            }
            return produtoDTOS;
        }
        return Collections.emptyList();
    }

    public void editar(Produto produto, Long id) {
        editarProdutoUseCase.editar(produto, id);
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
        eventPublisher.publishProductUpdatedEvent(produtoDTO);
    }

    public void remover(Long id) {
        Produto produto = buscarProdutoUseCase.buscar(id);
        removerProdutoUseCase.remover(id);
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
        eventPublisher.publishProductDeletedEvent(produtoDTO);
    }
}
