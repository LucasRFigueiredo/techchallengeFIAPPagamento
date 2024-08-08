package com.techchallenge.pagamento.application.usecases;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.application.events.PaymentService;
import com.techchallenge.pagamento.application.gateways.checkout.CheckoutUseCase;
import com.techchallenge.pagamento.domain.Checkout;
import com.techchallenge.pagamento.infrastructure.mapper.checkout.CheckoutMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoServiceImplTest {

    private PagamentoServiceImpl pagamentoService;
    private CheckoutUseCase checkoutUseCase;
    private PaymentService paymentService;
    private CheckoutMapper checkoutMapper;

    @BeforeEach
    public void setUp() {
        checkoutUseCase = mock(CheckoutUseCase.class);
        paymentService = mock(PaymentService.class);
        checkoutMapper = mock(CheckoutMapper.class);
        pagamentoService = new PagamentoServiceImpl(checkoutUseCase, paymentService, checkoutMapper);
    }

    @Test
    public void testAprovar() {
        Checkout checkout = new Checkout();
        when(checkoutUseCase.buscar(1L)).thenReturn(checkout);
        when(checkoutMapper.checkoutToCheckoutDTO(checkout)).thenReturn(new CheckoutDTO());

        pagamentoService.aprovar(1L);

        assertEquals("Aprovado", checkout.getPagamento());
        verify(checkoutUseCase, times(1)).atualizarStatus(checkout);
        verify(paymentService, times(1)).publishPaymentApprovedEvent(any(CheckoutDTO.class));
    }

    @Test
    public void testReprovar() {
        Checkout checkout = new Checkout();
        when(checkoutUseCase.buscar(1L)).thenReturn(checkout);
        when(checkoutMapper.checkoutToCheckoutDTO(checkout)).thenReturn(new CheckoutDTO());

        pagamentoService.reprovar(1L);

        assertEquals("Reprovado", checkout.getPagamento());
        verify(checkoutUseCase, times(1)).atualizarStatus(checkout);
        verify(paymentService, times(1)).publishPaymentRejectedEvent(any(CheckoutDTO.class));
    }
}
