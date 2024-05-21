package com.techchallenge.producao.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ped_seq_gen")
    @SequenceGenerator(name = "ped_seq_gen", sequenceName = "pedido_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "Cliente_id")
    private ClienteEntity cliente;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Pedido_Produto",
            joinColumns = @JoinColumn(name = "Pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "Produto_id"))
    private List<ProdutoEntity> produtos;
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private CheckoutEntity checkout;
}
