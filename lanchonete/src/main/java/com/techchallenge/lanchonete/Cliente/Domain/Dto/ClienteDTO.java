package com.techchallenge.lanchonete.Cliente.Domain.Dto;

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
