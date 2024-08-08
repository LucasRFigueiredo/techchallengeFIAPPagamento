package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.repository.cliente.SpringClienteRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.produto.SpringProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl {

    private final SpringPedidoRepository pedidoRepository;
    private final SpringProdutoRepository produtoRepository;
    private final SpringClienteRepository clienteRepository;
    private final ClienteEntityMapper clienteEntityMapper;
    private final ProdutoEntityMapper produtoEntityMapper;

    @Autowired
    public PedidoServiceImpl(SpringPedidoRepository pedidoRepository, SpringProdutoRepository produtoRepository, SpringClienteRepository clienteRepository, ClienteEntityMapper clienteEntityMapper, ProdutoEntityMapper produtoEntityMapper) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.clienteEntityMapper = clienteEntityMapper;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Transactional
    public Pedido salvarPedido(Pedido pedido) {
        return salvarPedidoComEntidades(pedido);
    }

    private Pedido salvarPedidoComEntidades(Pedido pedido) {
        ClienteEntity clienteEntity = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + pedido.getCliente().getId() + " não encontrado"));

        List<ProdutoEntity> produtoEntities = new ArrayList<>();
        for (Produto produto : pedido.getItens()) {
            ProdutoEntity produtoEntity = produtoRepository.findById(produto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + produto.getId() + " não encontrado"));
            produtoEntities.add(produtoEntity);
        }

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setCliente(clienteEntity);
        pedidoEntity.setStatus(pedido.getStatus());
        pedidoEntity.setProdutos(produtoEntities);
        PedidoEntity savedPedidoEntity = pedidoRepository.save(pedidoEntity);

        return new Pedido(savedPedidoEntity.getId(), clienteEntityMapper.clienteEntityToCliente(savedPedidoEntity.getCliente()), savedPedidoEntity.getStatus(), savedPedidoEntity.getProdutos().stream().map(produtoEntityMapper::produtoEntityToProduto).collect(Collectors.toList()));
    }
}
