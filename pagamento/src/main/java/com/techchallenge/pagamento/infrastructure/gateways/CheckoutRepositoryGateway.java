package com.techchallenge.pagamento.infrastructure.gateways;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.pagamento.infrastructure.mapper.produto.ProdutoEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.repository.checkout.SpringCheckoutRepository;
import com.techchallenge.pagamento.infrastructure.persistence.repository.pedido.SpringPedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckoutRepositoryGateway implements CheckoutUseCase {

    private final SpringCheckoutRepository springCheckoutRepository;
    private final SpringPedidoRepository springPedidoRepository;
    private final CheckoutEntityMapper checkoutEntityMapper;
    private final PedidoEntityMapper pedidoEntityMapper;
    private final ClienteEntityMapper clienteEntityMapper;
    private final ProdutoEntityMapper produtoEntityMapper;
    private final EntityManager entityManager;

    @Autowired
    public CheckoutRepositoryGateway(SpringCheckoutRepository springCheckoutRepository,
                                     SpringPedidoRepository springPedidoRepository,
                                     CheckoutEntityMapper checkoutEntityMapper,
                                     PedidoEntityMapper pedidoEntityMapper, ClienteEntityMapper clienteEntityMapper,
                                     ProdutoEntityMapper produtoEntityMapper, EntityManager entityManager) {
        this.springCheckoutRepository = springCheckoutRepository;
        this.springPedidoRepository = springPedidoRepository;
        this.checkoutEntityMapper = checkoutEntityMapper;
        this.pedidoEntityMapper = pedidoEntityMapper;
        this.clienteEntityMapper = clienteEntityMapper;
        this.produtoEntityMapper = produtoEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void criar(Checkout checkout) {
        CheckoutEntity checkoutEntity = checkoutEntityMapper.checkoutToCheckoutEntity(checkout);
        springCheckoutRepository.save(checkoutEntity);
    }

    @Override
    public List<Checkout> listar() {
        return springCheckoutRepository.findAll().stream()
                .map(checkoutEntityMapper::checkoutEntityToCheckout)
                .collect(Collectors.toList());
    }

    @Override
    public List<Checkout> buscarPorStatusPagamento(String statusPagamento) {
        return springCheckoutRepository.findByStatusPagamento(statusPagamento).stream()
                .map(checkoutEntityMapper::checkoutEntityToCheckout)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Pedido salvarPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoEntityMapper.pedidoToPedidoEntity(pedido);
        PedidoEntity savedPedidoEntity = springPedidoRepository.save(pedidoEntity);
        return mapToPedidoDomain(savedPedidoEntity);
    }

    @Override
    @Transactional
    public void atualizarStatus(Checkout checkout) {
        CheckoutEntity checkoutEntity = checkoutEntityMapper.checkoutToCheckoutEntity(checkout);
        springCheckoutRepository.save(checkoutEntity);
    }

    @Override
    public Checkout buscar(Long id) {
        return springCheckoutRepository.findById(id)
                .map(checkoutEntityMapper::checkoutEntityToCheckout)
                .orElse(null);
    }


    private Pedido mapToPedidoDomain(PedidoEntity pedidoEntity) {
        return new Pedido(
                pedidoEntity.getId(),
                clienteEntityMapper.clienteEntityToCliente(pedidoEntity.getCliente()),
                pedidoEntity.getStatus(),
                pedidoEntity.getProdutos().stream()
                        .map(produtoEntity -> new Produto(
                                produtoEntity.getId(),
                                produtoEntity.getTipo(),
                                produtoEntity.getNome(),
                                produtoEntity.getDescricao(),
                                produtoEntity.getPreco()))
                        .collect(Collectors.toList())
        );
    }
}
