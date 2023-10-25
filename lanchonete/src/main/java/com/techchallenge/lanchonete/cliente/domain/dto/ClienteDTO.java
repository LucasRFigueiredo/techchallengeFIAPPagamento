package com.techchallenge.lanchonete.cliente.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDTO {
    private String nome;
    private String cpf;
    private String email;
}
