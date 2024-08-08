package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.application.events.EventPublisher;
import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CheckoutServiceImplTest {

    private CheckoutUseCase checkoutUseCase;
    private CheckoutMapper checkoutMapper;
    private CheckoutServiceImpl checkoutService;
    private EventPublisher eventPublisher;

    @BeforeEach
    public void setUp() {
        checkoutUseCase = mock(CheckoutUseCase.class);
        checkoutMapper = mock(CheckoutMapper.class);
        checkoutService = new CheckoutServiceImpl(checkoutUseCase, checkoutMapper, eventPublisher);
    }

    /*@Test
    public void testCriar() {
        // Criação e configuração do produto
        Produto produto = new Produto();
        produto.setId(1L); // Defina um ID para evitar problemas de persistência
        produto.setPreco(10.0); // Defina um preço para o produto

        // Criação e configuração do pedido
        Pedido pedido = new Pedido();
        pedido.setItens(Collections.singletonList(produto));

        // Mock salvarPedido para retornar o pedido salvo
        Pedido savedPedido = new Pedido();
        savedPedido.setItens(Collections.singletonList(produto));
        Mockito.when(checkoutUseCase.salvarPedido(Mockito.any())).thenReturn(savedPedido);

        // ArgumentCaptor para capturar o checkout
        ArgumentCaptor<Checkout> checkoutCaptor = ArgumentCaptor.forClass(Checkout.class);
        Mockito.doNothing().when(checkoutUseCase).criar(checkoutCaptor.capture());

        // Executa o método criar
        checkoutService.criar(pedido);

        // Verificações
        Mockito.verify(checkoutUseCase, Mockito.times(1)).criar(Mockito.any());

        Checkout capturedCheckout = checkoutCaptor.getValue();
        assertEquals(BigDecimal.valueOf(10.0), capturedCheckout.getTotal());
        assertEquals("Aguardando pagamento", capturedCheckout.getPagamento());
        assertEquals("Aguardando pagamento", capturedCheckout.getStatus());
        assertEquals(savedPedido, capturedCheckout.getPedido());
    }*/

    @Test
    public void testBuscar() {
        CheckoutDTO checkoutDTO = new CheckoutDTO();
        List<CheckoutDTO> expectedCheckouts = Collections.singletonList(checkoutDTO);

        List<Checkout> checkouts = Collections.singletonList(new Checkout());
        Mockito.when(checkoutUseCase.listar()).thenReturn(checkouts);
        Mockito.when(checkoutMapper.checkoutToCheckoutDTO(Mockito.any())).thenReturn(checkoutDTO);

        List<CheckoutDTO> result = checkoutService.buscar();

        assertEquals(expectedCheckouts.size(), result.size());
        assertEquals(expectedCheckouts.get(0), result.get(0));
    }
}
