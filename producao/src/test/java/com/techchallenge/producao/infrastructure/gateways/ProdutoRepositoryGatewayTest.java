package com.techchallenge.producao.infrastructure.gateways;

import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.ProdutoEntity;
import com.techchallenge.producao.infrastructure.persistence.repository.produto.SpringProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProdutoRepositoryGatewayTest {

    @Mock
    private SpringProdutoRepository produtoRepository;

    @Mock
    private ProdutoEntityMapper produtoEntityMapper;

    @InjectMocks
    private ProdutoRepositoryGateway produtoRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializar os mocks

        produtoRepositoryGateway = new ProdutoRepositoryGateway(produtoRepository, produtoEntityMapper);
    }

    @Test
    public void testCriar() {
        Produto produto = new Produto();
        ProdutoEntity produtoEntity = new ProdutoEntity();

        when(produtoEntityMapper.produtoToProdutoEntity(produto)).thenReturn(produtoEntity);

        produtoRepositoryGateway.criar(produto);

        verify(produtoRepository, times(1)).save(produtoEntity);
    }

    @Test
    public void testBuscarTipo() {
        String tipo = "tipo";
        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntities.add(produtoEntity);

        when(produtoRepository.findByTipo(tipo)).thenReturn(produtoEntities);
        when(produtoEntityMapper.produtoEntityToProduto(produtoEntity)).thenReturn(new Produto());

        List<Produto> produtos = produtoRepositoryGateway.buscarTipo(tipo);

        assertEquals(1, produtos.size());
    }

    @Test
    public void testEditar() {
        Produto produto = new Produto();
        Long id = 1L;

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(id);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));

        produtoRepositoryGateway.editar(produto, id);

        verify(produtoRepository, times(1)).save(any(ProdutoEntity.class));
    }

    @Test
    public void testRemover() {
        Long id = 1L;

        produtoRepositoryGateway.remover(id);

        verify(produtoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testBuscar() {
        Long id = 1L;
        ProdutoEntity produtoEntity = new ProdutoEntity();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));
        when(produtoEntityMapper.produtoEntityToProduto(produtoEntity)).thenReturn(new Produto());

        Produto produto = produtoRepositoryGateway.buscar(id);

        assertEquals(Produto.class, produto.getClass());
    }
}
