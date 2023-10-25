package com.techchallenge.lanchonete.cliente.port.repository;

import com.techchallenge.lanchonete.cliente.domain.entity.Cliente;

public interface ClienteRepositoryPort {
    void salvar(Cliente cliente);
    Cliente buscar(String cpf);
}
