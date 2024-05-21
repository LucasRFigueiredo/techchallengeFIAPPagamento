package com.techchallenge.pagamento.domain;

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
    private Cliente cliente;
    private String status;
    private List<Produto> itens;
}
