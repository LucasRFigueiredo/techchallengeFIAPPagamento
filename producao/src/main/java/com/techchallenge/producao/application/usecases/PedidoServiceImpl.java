package com.techchallenge.producao.application.usecases;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.gateways.pedido.CriarPedidoUseCase;
import com.techchallenge.producao.application.gateways.pedido.ListarPedidoUseCase;
import com.techchallenge.producao.application.gateways.produto.BuscarProdutoUseCase;
import com.techchallenge.producao.application.events.EventPublisher;
import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.gateways.CheckoutWebClient;
import com.techchallenge.producao.infrastructure.gateways.ClienteWebClient;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoMapper;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl {
    private final CriarPedidoUseCase criarPedidoUseCase;
    private final ListarPedidoUseCase listarPedidoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final PedidoMapper pedidoMapper;
    private final ClienteWebClient clienteWebClient;
    private final CheckoutWebClient checkoutWebClient;
    private final EventPublisher eventPublisher;

    public PedidoServiceImpl(CriarPedidoUseCase criarPedidoUseCase, ListarPedidoUseCase listarPedidoUseCase,
                             BuscarProdutoUseCase buscarProdutoUseCase, PedidoMapper pedidoMapper,
                             ClienteWebClient clienteWebClient, CheckoutWebClient checkoutWebClient,
                             EventPublisher eventPublisher) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.listarPedidoUseCase = listarPedidoUseCase;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.pedidoMapper = pedidoMapper;
        this.clienteWebClient = clienteWebClient;
        this.checkoutWebClient = checkoutWebClient;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void criar(PedidoDTO pedidoDTO) {
        pedidoDTO.setCliente(clienteWebClient.getClienteByCPF(pedidoDTO.getCliente().getCpf()));
        Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);

        List<Produto> produtos = Optional.ofNullable(pedido.getItens()).orElse(Collections.emptyList());
        if (!produtos.isEmpty()) {
            for (Produto produto : produtos) {
                buscarProdutoUseCase.buscar(produto.getId());
                //TODO logica de estoque no futuro?
            }
        }
        Long pedidoId = criarPedidoUseCase.criar(pedido);
        pedido.setId(pedidoId);
        checkoutWebClient.criaCheckout(pedidoMapper.pedidoToPedidoDTO(pedido));
        eventPublisher.publishOrderCreatedEvent(pedidoDTO);
    }

    public List<PedidoDTO> listar() {
        List<Pedido> pedidos = listarPedidoUseCase.listar();
        if (!pedidos.isEmpty()) {
            List<PedidoDTO> pedidoDTOS = new ArrayList<>();
            for (Pedido pedido : pedidos) {
                PedidoDTO pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedido);
                pedidoDTOS.add(pedidoDTO);
            }
            return pedidoDTOS;
        } else {
            return Collections.emptyList();
        }
    }
}
