package com.techchallenge.lanchonete.pedido.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Long id;
    private String cliente;
    private List<String> itens;
}
