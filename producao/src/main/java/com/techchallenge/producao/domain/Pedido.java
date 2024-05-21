package com.techchallenge.producao.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pedido {
    private Long id;
    private Cliente cliente;
    private String status;
    private List<Produto> itens;
}
