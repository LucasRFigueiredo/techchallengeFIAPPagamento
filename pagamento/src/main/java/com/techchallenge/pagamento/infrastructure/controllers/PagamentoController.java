package com.techchallenge.pagamento.infrastructure.controllers;

import com.techchallenge.pagamento.application.dto.CheckoutDTO;
import com.techchallenge.pagamento.application.dto.PedidoDTO;
import com.techchallenge.pagamento.application.events.EventPublisher;
import com.techchallenge.pagamento.application.usecases.CheckoutServiceImpl;
import com.techchallenge.pagamento.application.usecases.PagamentoServiceImpl;
import com.techchallenge.pagamento.infrastructure.mapper.pedido.PedidoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {
    private final PagamentoServiceImpl pagamentoService;
    private final CheckoutServiceImpl checkoutService;
    private final PedidoMapper pedidoMapper;
    private final EventPublisher eventPublisher;

    public PagamentoController(PagamentoServiceImpl pagamentoService, CheckoutServiceImpl checkoutService, PedidoMapper pedidoMapper, EventPublisher eventPublisher) {
        this.pagamentoService = pagamentoService;
        this.checkoutService = checkoutService;
        this.pedidoMapper = pedidoMapper;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(value = "/checkout")
    void registrarCheckout(@RequestBody PedidoDTO pedidoDTO) {
        checkoutService.criar(pedidoMapper.pedidoDTOToPedido(pedidoDTO));
    }

    @GetMapping(value = "/listar")
    List<CheckoutDTO> checkout() {
        return checkoutService.buscar();
    }

    @GetMapping(value = "/aprovar/{id}")
    void aprovar(@PathVariable Long id) {
        pagamentoService.aprovar(id);
        eventPublisher.publishPaymentApprovedEvent(id.toString());
    }

    @GetMapping(value = "/reprovar/{id}")
    void reprovar(@PathVariable Long id) {
        pagamentoService.reprovar(id);
        eventPublisher.publishPaymentRejectedEvent(id.toString());
    }
}
