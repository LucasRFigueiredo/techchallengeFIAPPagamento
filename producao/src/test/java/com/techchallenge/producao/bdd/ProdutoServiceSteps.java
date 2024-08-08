package com.techchallenge.producao.bdd;

import com.techchallenge.producao.application.events.EventPublisher;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    private Produto produto;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoService = new ProdutoServiceImpl(criarProdutoUseCase, buscarTipoProdutoUseCase, null, editarProdutoUseCase, removerProdutoUseCase, produtoMapper, eventPublisher);
    }

    @Dado("que eu tenha um produto válido")
    public void queEuTenhaUmProdutoValido() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do Produto Teste");
        produto.setPreco(100.0);
    }

    @Quando("eu chamar o método para criar um produto")
    public void euChamarOMetodoParaCriarUmProduto() {
        doNothing().when(criarProdutoUseCase).criar(produto);
        doNothing().when(eventPublisher).publishProductCreatedEvent(any());
        produtoService.criar(produto);
    }

    @Então("o produto deve ser criado com sucesso")
    public void oProdutoDeveSerCriadoComSucesso() {
        verify(criarProdutoUseCase, times(1)).criar(produto);
        verify(eventPublisher, times(1)).publishProductCreatedEvent(any());
    }
}
