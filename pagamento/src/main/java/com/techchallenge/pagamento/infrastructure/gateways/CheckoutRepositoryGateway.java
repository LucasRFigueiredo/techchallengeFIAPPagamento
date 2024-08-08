package com.techchallenge.pagamento.infrastructure.gateways;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.domain.Cliente;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.repository.checkout.SpringCheckoutRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.cliente.SpringClienteRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.produto.SpringProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutRepositoryGateway implements CheckoutUseCase {
    private final SpringClienteRepository clienteRepository;
    private final SpringProdutoRepository produtoRepository;
    private final SpringPedidoRepository pedidoRepository;
    private final SpringCheckoutRepository checkoutRepository;
    private final ClienteEntityMapper clienteEntityMapper;
    private final ProdutoEntityMapper produtoEntityMapper;
    private final PedidoEntityMapper pedidoEntityMapper;
    private final CheckoutEntityMapper checkoutEntityMapper;

    @Autowired
    public CheckoutRepositoryGateway(SpringClienteRepository clienteRepository, SpringProdutoRepository produtoRepository, SpringPedidoRepository pedidoRepository, SpringCheckoutRepository checkoutRepository, ClienteEntityMapper clienteEntityMapper, ProdutoEntityMapper produtoEntityMapper, PedidoEntityMapper pedidoEntityMapper, CheckoutEntityMapper checkoutEntityMapper) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.checkoutRepository = checkoutRepository;
        this.clienteEntityMapper = clienteEntityMapper;
        this.produtoEntityMapper = produtoEntityMapper;
        this.pedidoEntityMapper = pedidoEntityMapper;
        this.checkoutEntityMapper = checkoutEntityMapper;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = clienteEntityMapper.clienteToClienteEntity(cliente);
        ClienteEntity savedClienteEntity = clienteRepository.save(clienteEntity);
        return clienteEntityMapper.clienteEntityToCliente(savedClienteEntity);
    }

    @Override
    public Produto salvarProduto(Produto produto) {
        ProdutoEntity produtoEntity = produtoEntityMapper.produtoToProdutoEntity(produto);
        ProdutoEntity savedProdutoEntity = produtoRepository.save(produtoEntity);
        return produtoEntityMapper.produtoEntityToProduto(savedProdutoEntity);
    }

    @Override
    public Pedido salvarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoEntityMapper.pedidoToPedidoEntity(pedido);
        PedidoEntity savedPedidoEntity = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.pedidoEntityToPedido(savedPedidoEntity);
    }

    @Override
    public void criar(Checkout checkout) {
        CheckoutEntity checkoutEntity = checkoutEntityMapper.checkoutToCheckoutEntity(checkout);
        checkoutRepository.save(checkoutEntity);
    }

    @Override
    public List<Checkout> listar() {
        List<CheckoutEntity> checkoutEntities = checkoutRepository.findAll();
        return checkoutEntities.stream()
                .map(checkoutEntityMapper::checkoutEntityToCheckout)
                .collect(Collectors.toList());
    }

    @Override
    public List<Checkout> buscarPorStatusPagamento(String statusPagamento) {
        List<CheckoutEntity> checkoutEntities = checkoutRepository.findByStatusPagamento(statusPagamento);
        return checkoutEntities.stream()
                .map(checkoutEntityMapper::checkoutEntityToCheckout)
                .collect(Collectors.toList());
    }

    @Override
    public void atualizarStatus(Checkout checkout) {
        CheckoutEntity checkoutEntity = checkoutEntityMapper.checkoutToCheckoutEntity(checkout);
        checkoutRepository.save(checkoutEntity);
    }

    @Override
    public Checkout buscar(Long id) {
        Optional<CheckoutEntity> checkoutEntityOptional = checkoutRepository.findById(id);
        return checkoutEntityOptional.map(checkoutEntityMapper::checkoutEntityToCheckout).orElse(null);
    }
}
