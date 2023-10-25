package com.techchallenge.lanchonete.Pedido.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private String cliente;
    private List<String> itens;
}
