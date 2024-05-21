package com.techchallenge.pagamento.infrastructure.controllers;

import com.techchallenge.pagamento.application.dto.PedidoDTO;
import com.techchallenge.pagamento.application.usecases.CheckoutServiceImpl;
import com.techchallenge.pagamento.application.usecases.PagamentoServiceImpl;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

class PagamentoControllerTest {
    @Mock
    private PagamentoServiceImpl pagamentoService;

    @Mock
    private CheckoutServiceImpl checkoutService;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldTestIfCriarCheckoutIsBeingCalled() {
        pagamentoController.registrarCheckout(new PedidoDTO());
        verify(checkoutService).criar(any());
    }

    @Test
    public void shouldTestIfListarCheckoutIsBeingCalled() {
        pagamentoController.checkout();
        verify(checkoutService).buscar();
    }

    @Test
    public void shouldTestIfAprovarPagamentoIsBeingCalled() {
        pagamentoController.aprovar(1L);
        verify(pagamentoService).aprovar(any());
    }

    @Test
    public void shouldTestIfReprovarPagamentoIsBeingCalled() {
        pagamentoController.reprovar(1L);
        verify(pagamentoService).reprovar(any());
    }
}