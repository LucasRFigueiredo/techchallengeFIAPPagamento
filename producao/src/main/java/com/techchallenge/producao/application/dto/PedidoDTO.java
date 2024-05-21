package com.techchallenge.producao.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private ClienteDTO cliente;
    private String status;
    private List<ProdutoDTO> itens;
}
