package com.techchallenge.producao.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.techchallenge.producao.application.gateways.produto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;

public class ProdutoServiceImplTest {

    private CriarProdutoUseCase criarProdutoUseCase;
    private BuscarTipoProdutoUseCase buscarTipoProdutoUseCase;
    private BuscarProdutoUseCase buscarProdutoUseCase;
    private EditarProdutoUseCase editarProdutoUseCase;
    private RemoverProdutoUseCase removerProdutoUseCase;
    private ProdutoMapper produtoMapper;
    private ProdutoServiceImpl produtoService;

    @BeforeEach
    public void setUp() {
        criarProdutoUseCase = mock(CriarProdutoUseCase.class);
        buscarTipoProdutoUseCase = mock(BuscarTipoProdutoUseCase.class);
        editarProdutoUseCase = mock(EditarProdutoUseCase.class);
        removerProdutoUseCase = mock(RemoverProdutoUseCase.class);
        produtoMapper = mock(ProdutoMapper.class);

        produtoService = new ProdutoServiceImpl(criarProdutoUseCase, buscarTipoProdutoUseCase, buscarProdutoUseCase,
                editarProdutoUseCase, removerProdutoUseCase, produtoMapper);
    }

    @Test
    public void testBuscarTipo() {
        // Configuração do mock para simular a busca de produtos por tipo
        List<Produto> produtos = Arrays.asList(new Produto(), new Produto()); // Criando uma lista de produtos simulada
        when(buscarTipoProdutoUseCase.buscarTipo("tipo")).thenReturn(produtos);

        // Configuração do mock para simular o mapeamento de produtos para DTOs
        ProdutoDTO produtoDTO1 = new ProdutoDTO(); // Criando um ProdutoDTO simulado
        ProdutoDTO produtoDTO2 = new ProdutoDTO(); // Criando um ProdutoDTO simulado
        when(produtoMapper.produtoToProdutoDTO(any(Produto.class))).thenReturn(produtoDTO1, produtoDTO2);

        // Execução do método a ser testado
        List<ProdutoDTO> resultado = produtoService.buscarTipo("tipo");

        // Verificação dos resultados
        assertEquals(2, resultado.size());
        verify(buscarTipoProdutoUseCase, times(1)).buscarTipo("tipo");
        verify(produtoMapper, times(2)).produtoToProdutoDTO(any(Produto.class));
    }

    // Adicione outros métodos de teste conforme necessário para cobrir os casos de uso restantes
}
