package com.techchallenge.pagamento.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    private Long id;
    private Pedido pedido;
    private BigDecimal total;
    private String pagamento;
    private String status;
}
