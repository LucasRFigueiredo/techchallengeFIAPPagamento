package com.techchallenge.producao.infrastructure.controllers;

import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.application.usecases.ProdutoServiceImpl;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private final ProdutoServiceImpl produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoServiceImpl produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @PostMapping
    void criarProduto(@RequestBody ProdutoDTO produtoDto) {
        Produto produto = produtoMapper.produtoDTOToProduto(produtoDto);
        produtoService.criar(produto);
    }

    @GetMapping(value = "/{tipo}")
    List<ProdutoDTO> buscarProdutoPorTipo(@PathVariable String tipo) {
        List<ProdutoDTO> produtoDTOS = produtoService.buscarTipo(tipo);
        return produtoDTOS;
    }

    @PutMapping(value = "/{id}")
    void editarProduto(@RequestBody ProdutoDTO produtoDto, @PathVariable Long id) {
        Produto produto = produtoMapper.produtoDTOToProduto(produtoDto);
        produtoService.editar(produto, id);
    }

    @DeleteMapping(value = "/{id}")
    void removerProduto(@PathVariable Long id) {
        produtoService.remover(id);
    }
}
