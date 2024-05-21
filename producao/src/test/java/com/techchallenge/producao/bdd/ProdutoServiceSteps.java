package com.techchallenge.producao.bdd;

import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.application.gateways.produto.BuscarTipoProdutoUseCase;
import com.techchallenge.producao.application.gateways.produto.CriarProdutoUseCase;
import com.techchallenge.producao.application.gateways.produto.EditarProdutoUseCase;
import com.techchallenge.producao.application.gateways.produto.RemoverProdutoUseCase;
import com.techchallenge.producao.application.usecases.ProdutoServiceImpl;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ProdutoServiceSteps {

    @Mock
    private CriarProdutoUseCase criarProdutoUseCase;

    @Mock
    private BuscarTipoProdutoUseCase buscarTipoProdutoUseCase;

    @Mock
    private EditarProdutoUseCase editarProdutoUseCase;

    @Mock
    private RemoverProdutoUseCase removerProdutoUseCase;

    @Mock
    private ProdutoMapper produtoMapper;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    private Produto produto;
    private List<Produto> produtos;
    private List<ProdutoDTO> produtoDTOS;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Dado("que eu tenha um produto válido")
    public void givenValidProduct() {
        produto = new Produto();
    }

    @Quando("eu chamar o método para criar um produto")
    public void whenCreateProductMethodIsCalled() {
        // Configuração do comportamento do mock
        doNothing().when(criarProdutoUseCase).criar(produto);

        // Chamada do método a ser testado
        produtoService.criar(produto);
    }

    @Então("o produto deve ser criado com sucesso")
    public void thenProductShouldBeCreatedSuccessfully() {
        // Verificação se o método do caso de uso correspondente foi chamado
        verify(criarProdutoUseCase, times(1)).criar(produto);
    }
}
