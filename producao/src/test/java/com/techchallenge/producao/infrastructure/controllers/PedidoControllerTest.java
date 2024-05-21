package com.techchallenge.producao.infrastructure.controllers;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.usecases.PedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class PedidoControllerTest {

    @Mock
    private PedidoServiceImpl pedidoService;
    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShouldVerifyIfCriarPedidoIsBeingCalled() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoController.criarPedido(pedidoDTO);
        verify(pedidoService).criar(any());
    }

    @Test
    public void testShouldVerifyIfListarPedidoIsBeingCalled() {
        pedidoController.listaPedidos();
        verify(pedidoService).listar();
    }
}