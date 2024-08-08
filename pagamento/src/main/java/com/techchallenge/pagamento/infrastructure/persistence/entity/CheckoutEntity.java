package com.techchallenge.pagamento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Checkout")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkout_seq_gen")
    @SequenceGenerator(name = "checkout_seq_gen", sequenceName = "checkout_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    private BigDecimal total;
    private String pagamento;
    private String status;
}
