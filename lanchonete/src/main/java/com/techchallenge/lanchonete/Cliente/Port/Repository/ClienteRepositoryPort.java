package com.techchallenge.lanchonete.Cliente.Port.Repository;

import com.techchallenge.lanchonete.Cliente.Domain.Entity.Cliente;

public interface ClienteRepositoryPort {
    void salvar(Cliente cliente);
    Cliente buscar(String cpf);
}
