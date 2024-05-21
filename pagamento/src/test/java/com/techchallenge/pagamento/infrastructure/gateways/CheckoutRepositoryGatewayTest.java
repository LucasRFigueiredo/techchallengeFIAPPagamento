package com.techchallenge.pagamento.infrastructure.gateways;

import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.domain.Cliente;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutEntityMapper;
import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import com.techchallenge.pagamento.infrastructure.persistence.repository.checkout.SpringCheckoutRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CheckoutRepositoryGatewayTest {

    @Mock
    private SpringCheckoutRepository springCheckoutRepository;

    @Mock
    private CheckoutEntityMapper checkoutEntityMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CheckoutRepositoryGateway checkoutRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    public void testCriar() {
        // Prepare data
        Cliente cliente = new Cliente("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com");
        Produto produto = new Produto(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0);
        Pedido pedido = new Pedido(1L, cliente, "Pendente", Collections.singletonList(produto));
        Checkout checkout = new Checkout(1L, pedido, new BigDecimal("10.00"), "Cartão", "Pendente");

        ClienteEntity clienteEntity = new ClienteEntity("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com", null);
        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0, null);
        PedidoEntity pedidoEntity = new PedidoEntity(1L, clienteEntity, "Pendente", Collections.singletonList(produtoEntity), null);
        CheckoutEntity checkoutEntity = new CheckoutEntity(1L, pedidoEntity, new BigDecimal("10.00"), "Cartão", "Pendente");

        // Configure the mock to return empty when the ID does not exist
        when(springCheckoutRepository.findById(1L)).thenReturn(Optional.of(checkoutEntity));

        // Configure the mock to return the mapped entity
        when(checkoutEntityMapper.checkoutToCheckoutEntity(checkout)).thenReturn(checkoutEntity);

        // Mock the merge operations
        when(entityManager.merge(any(ClienteEntity.class))).thenReturn(clienteEntity);
        when(entityManager.merge(any(PedidoEntity.class))).thenReturn(pedidoEntity);
        when(entityManager.merge(any(ProdutoEntity.class))).thenReturn(produtoEntity);
        when(entityManager.merge(any(CheckoutEntity.class))).thenReturn(checkoutEntity);

        // Execute the method
        checkoutRepositoryGateway.criar(checkout);

        // Verify interactions and state
        verify(springCheckoutRepository, times(1)).save(checkoutEntity);
    }

    @Test
    public void testListar() {
        // Prepare data
        ClienteEntity clienteEntity = new ClienteEntity("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com", null);
        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0, null);
        PedidoEntity pedidoEntity = new PedidoEntity(1L, clienteEntity, "Pendente", Collections.singletonList(produtoEntity), null);
        CheckoutEntity checkoutEntity = new CheckoutEntity(1L, pedidoEntity, new BigDecimal("10.00"), "Cartão", "Pendente");
        List<CheckoutEntity> checkoutEntities = Collections.singletonList(checkoutEntity);

        Cliente cliente = new Cliente("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com");
        Produto produto = new Produto(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0);
        Pedido pedido = new Pedido(1L, cliente, "Pendente", Collections.singletonList(produto));
        Checkout checkout = new Checkout(1L, pedido, new BigDecimal("10.00"), "Cartão", "Pendente");

        when(springCheckoutRepository.findAll()).thenReturn(checkoutEntities);
        when(checkoutEntityMapper.checkoutEntityToCheckout(checkoutEntity)).thenReturn(checkout);

        // Execute the method
        List<Checkout> checkouts = checkoutRepositoryGateway.listar();

        // Verify results
        assertNotNull(checkouts);
        assertEquals(1, checkouts.size());
        assertEquals(checkout, checkouts.get(0));
    }

    @Test
    public void testBuscar() {
        // Prepare data
        ClienteEntity clienteEntity = new ClienteEntity("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com", null);
        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0, null);
        PedidoEntity pedidoEntity = new PedidoEntity(1L, clienteEntity, "Pendente", Collections.singletonList(produtoEntity), null);
        CheckoutEntity checkoutEntity = new CheckoutEntity(1L, pedidoEntity, new BigDecimal("10.00"), "Cartão", "Pendente");

        Cliente cliente = new Cliente("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com");
        Produto produto = new Produto(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0);
        Pedido pedido = new Pedido(1L, cliente, "Pendente", Collections.singletonList(produto));
        Checkout checkout = new Checkout(1L, pedido, new BigDecimal("10.00"), "Cartão", "Pendente");

        when(springCheckoutRepository.findById(1L)).thenReturn(Optional.of(checkoutEntity));
        when(checkoutEntityMapper.checkoutEntityToCheckout(checkoutEntity)).thenReturn(checkout);

        // Execute the method
        Checkout result = checkoutRepositoryGateway.buscar(1L);

        // Verify results
        assertNotNull(result);
        assertEquals(checkout, result);
    }

    @Test
    public void testBuscarPorStatusPagamento() {
        // Prepare data
        ClienteEntity clienteEntity = new ClienteEntity("664c22ed062528092ab45298", "João", "12345678900", "joao@example.com", null);
        ProdutoEntity produtoEntity = new ProdutoEntity(1L, "Bebida", "Coca-Cola", "Refrigerante 350ml", 5.0, null);
        PedidoEntity pedidoEntity = new PedidoEntity(1L, clienteEntity, "Pendente", Collections.singletonList(produtoEntity), null);
        CheckoutEntity checkoutEntity = new CheckoutEntity(1L, pedidoEntity, new BigDecimal("10.00"), "Cartão", "Pago");
        List<CheckoutEntity> checkoutEntities = Collections.singletonList(checkoutEntity);

        Checkout checkout = new Checkout(1L, new Pedido(), new BigDecimal("10.00"), "Cartão", "Pago");

        when(springCheckoutRepository.findByStatusPagamento("Pago")).thenReturn(checkoutEntities);
        when(checkoutEntityMapper.checkoutEntitiesToCheckouts(checkoutEntities)).thenReturn(Collections.singletonList(checkout));

        // Execute the method
        List<Checkout> result = checkoutRepositoryGateway.buscarPorStatusPagamento("Pago");

        // Verify results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(checkout, result.get(0));
    }
}
