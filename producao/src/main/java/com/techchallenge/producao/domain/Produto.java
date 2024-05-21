package com.techchallenge.producao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Long Id;
    private String tipo;
    private String nome;
    private String descricao;
    private Double preco;
}
