package com.techchallenge.producao.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq_gen")
    @SequenceGenerator(name = "prod_seq_gen", sequenceName = "produto_seq", allocationSize = 1)
    private Long Id;
    private String tipo;
    private String nome;
    private String descricao;
    private Double preco;

    @ManyToMany(mappedBy = "produtos")
    private List<PedidoEntity> pedidos;
}
