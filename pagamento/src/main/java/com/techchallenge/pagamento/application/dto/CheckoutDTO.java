package com.techchallenge.pagamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO {
    private Long id;
    private PedidoDTO pedido;
    private BigDecimal total;
    private String pagamento;
    private String status;
}
