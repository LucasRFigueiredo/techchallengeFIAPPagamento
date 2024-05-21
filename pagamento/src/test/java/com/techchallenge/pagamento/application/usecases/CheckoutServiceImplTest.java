package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.domain.Pedido;
import com.techchallenge.pagamento.domain.Produto;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CheckoutServiceImplTest {

    private CheckoutUseCase checkoutUseCase;
    private CheckoutMapper checkoutMapper;
    private CheckoutServiceImpl checkoutService;

    @BeforeEach
    public void setUp() {
        checkoutUseCase = mock(CheckoutUseCase.class);
        checkoutMapper = mock(CheckoutMapper.class);
        checkoutService = new CheckoutServiceImpl(checkoutUseCase, checkoutMapper);
    }

    @Test
    public void testCriar() {
        Produto produto = new Produto();
        produto.setPreco(10.0);
        List<Produto> produtos = Collections.singletonList(produto);
        Pedido pedido = new Pedido();
        pedido.setItens(produtos);

        ArgumentCaptor<Checkout> checkoutCaptor = ArgumentCaptor.forClass(Checkout.class);
        Mockito.doNothing().when(checkoutUseCase).criar(checkoutCaptor.capture());

        checkoutService.criar(pedido);

        Mockito.verify(checkoutUseCase, Mockito.times(1)).criar(Mockito.any());

        Checkout capturedCheckout = checkoutCaptor.getValue();
        assertEquals(BigDecimal.valueOf(10.0), capturedCheckout.getTotal());
        assertEquals("Aguardando pagamento", capturedCheckout.getPagamento());
        assertEquals("Aguardando pagamento", capturedCheckout.getStatus());
        assertEquals(pedido, capturedCheckout.getPedido());
    }

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
