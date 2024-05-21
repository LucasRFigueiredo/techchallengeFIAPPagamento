package com.techchallenge.producao.infrastructure.controllers;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.usecases.PedidoServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
    private final PedidoServiceImpl pedidoService;

    public PedidoController(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    void criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        pedidoService.criar(pedidoDTO);
    }

    @GetMapping
    List<PedidoDTO> listaPedidos() {
        return pedidoService.listar();
    }
}
