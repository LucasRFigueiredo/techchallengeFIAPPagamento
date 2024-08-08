package com.techchallenge.producao.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.techchallenge.producao.application.dto.ClienteDTO;
import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.application.events.EventPublisher;
import com.techchallenge.producao.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.gateways.pedido.CriarPedidoUseCase;
import com.techchallenge.producao.application.gateways.pedido.ListarPedidoUseCase;
import com.techchallenge.producao.application.gateways.produto.BuscarProdutoUseCase;
import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.gateways.CheckoutWebClient;
import com.techchallenge.producao.infrastructure.gateways.ClienteWebClient;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoMapper;

public class PedidoServiceImplTest {
    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;
    @Mock
    private ListarPedidoUseCase listarPedidoUseCase;
    @Mock
    private BuscarProdutoUseCase buscarProdutoUseCase;
    @Mock
    private PedidoMapper pedidoMapper;
    @Mock
    private ClienteWebClient clienteWebClient;
    @Mock
    private CheckoutWebClient checkoutWebClient;
    @Mock
    private EventPublisher eventPublisher;
    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarPedido() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(1L);
        pedidoDTO.setStatus("Entregue");
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNome("Fulano da Silva");
        clienteDTO.setCpf("12345678901");
        pedidoDTO.setCliente(clienteDTO);

        ProdutoDTO produtoDTO1 = new ProdutoDTO();
        ProdutoDTO produtoDTO2 = new ProdutoDTO();
        produtoDTO1.setId(1L);
        produtoDTO1.setNome("Hambúrguer");
        produtoDTO1.setDescricao("Hambúrguer delicioso");
        produtoDTO2.setId(2L);
        produtoDTO2.setNome("Hambúrguer");
        produtoDTO2.setDescricao("Hambúrguer delicioso");
        pedidoDTO.setItens(Arrays.asList(produtoDTO1, produtoDTO2));

        when(clienteWebClient.getClienteByCPF(anyString())).thenReturn(clienteDTO);

        Produto produto1 = new Produto(1L, "lanche", "hamburger com bacon", "hamburger com queijo e bacon", 15.0);
        Produto produto2 = new Produto(2L, "lanche", "hamburger", "hamburger com queijo", 10.0);
        when(buscarProdutoUseCase.buscar(1L)).thenReturn(produto1);
        when(buscarProdutoUseCase.buscar(2L)).thenReturn(produto2);

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus("Entregue");
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        pedido.setCliente(cliente);
        pedido.setItens(Arrays.asList(produto1, produto2));
        when(pedidoMapper.pedidoDTOToPedido(pedidoDTO)).thenReturn(pedido);

        when(criarPedidoUseCase.criar(pedido)).thenReturn(1L);

        pedidoService.criar(pedidoDTO);

        verify(clienteWebClient).getClienteByCPF(pedidoDTO.getCliente().getCpf());
        verify(buscarProdutoUseCase, times(2)).buscar(anyLong());
        verify(criarPedidoUseCase).criar(pedido);
        verify(eventPublisher).publishOrderCreatedEvent(pedidoDTO);
    }

    @Test
    public void testListarPedidos() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus("Entregue");
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        pedido.setCliente(cliente);
        List<Produto> produtos = new ArrayList<>();
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Hambúrguer");
        produto.setDescricao("Hambúrguer delicioso");
        produtos.add(produto);
        pedido.setItens(produtos);
        when(listarPedidoUseCase.listar()).thenReturn(Arrays.asList(pedido));
        PedidoDTO pedidoDTO = new PedidoDTO();
        when(pedidoMapper.pedidoToPedidoDTO(pedido)).thenReturn(pedidoDTO);

        List<PedidoDTO> resultado = pedidoService.listar();

        assertEquals(1, resultado.size());
        assertSame(pedidoDTO, resultado.get(0));
    }
}
