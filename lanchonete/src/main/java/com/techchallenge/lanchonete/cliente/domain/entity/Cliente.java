package com.techchallenge.lanchonete.cliente.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private UUID id;
    private String nome;
    private String cpf;
    private String email;
}
