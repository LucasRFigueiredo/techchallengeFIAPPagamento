package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PagamentoServiceImplTest {

    private PagamentoServiceImpl pagamentoService;
    private CheckoutUseCase checkoutUseCase;

    @BeforeEach
    public void setUp() {
        checkoutUseCase = mock(CheckoutUseCase.class);
        pagamentoService = new PagamentoServiceImpl(checkoutUseCase);
    }

    @Test
    public void testAprovar() {
        Checkout checkout = new Checkout();
        when(checkoutUseCase.buscar(1L)).thenReturn(checkout);

        pagamentoService.aprovar(1L);
        verify(checkoutUseCase, times(1)).atualizarPagamento(checkout);
    }

    @Test
    public void testReprovar() {
        Checkout checkout = new Checkout();
        when(checkoutUseCase.buscar(1L)).thenReturn(checkout);

        pagamentoService.reprovar(1L);

        verify(checkoutUseCase, times(1)).atualizarPagamento(checkout);
    }
}
