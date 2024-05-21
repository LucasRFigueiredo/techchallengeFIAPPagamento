package com.techchallenge.producao.infrastructure.gateways;

import com.techchallenge.producao.application.gateways.produto.*;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.ProdutoEntity;
import com.techchallenge.producao.infrastructure.persistence.repository.produto.SpringProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoRepositoryGateway implements CriarProdutoUseCase, BuscarTipoProdutoUseCase, EditarProdutoUseCase,
        RemoverProdutoUseCase, BuscarProdutoUseCase {
    private final SpringProdutoRepository springProdutoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    public ProdutoRepositoryGateway(SpringProdutoRepository springProdutoRepository, ProdutoEntityMapper produtoEntityMapper) {
        this.springProdutoRepository = springProdutoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public void criar(Produto produto) {
        ProdutoEntity produtoEntity = produtoEntityMapper.produtoToProdutoEntity(produto);
        springProdutoRepository.save(produtoEntity);
    }

    @Override
    public List<Produto> buscarTipo(String tipo) {
        List<ProdutoEntity> produtoEntities = this.springProdutoRepository.findByTipo(tipo);
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoEntity produtoEntity : produtoEntities) {
            Produto produto = new Produto();
            produto.setTipo(produtoEntity.getTipo());
            produto.setId(produtoEntity.getId());
            produto.setDescricao(produtoEntity.getDescricao());
            produto.setNome(produtoEntity.getNome());
            produto.setPreco(produtoEntity.getPreco());
            produtos.add(produto);
        }
        return produtos;
    }


    @Override
    public void editar(Produto produto, Long id) {
        ProdutoEntity produtoById = springProdutoRepository.findById(id).get();
        if (!Objects.isNull(produtoById)) {
            produtoById = produtoEntityMapper.updateProdutoEntity(produto, produtoById);
            this.springProdutoRepository.save(produtoById);
        } else {
            throw new RuntimeException("Produto inexistente, realize o cadastro com o criar");
        }
    }

    @Override
    public void remover(Long id) {
        springProdutoRepository.deleteById(id);
    }

    @Override
    public Produto buscar(Long id) {
        ProdutoEntity produtoById = springProdutoRepository.findById(id).get();
        if (!Objects.isNull(produtoById)) {
            return produtoEntityMapper.produtoEntityToProduto(produtoById);
        } else {
            throw new RuntimeException("Produto inexistente, realize o cadastro com o criar");
        }
    }
}
