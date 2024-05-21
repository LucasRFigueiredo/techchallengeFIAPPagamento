package com.techchallenge.producao.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    @Id
    private String id;
    private String nome;
    private String cpf;
    private String email;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos;
}
