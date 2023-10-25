package com.techchallenge.lanchonete.Produto.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoDto {
    private String tipo;
    private String nome;
    private String descricao;
    private Double preco;
}
