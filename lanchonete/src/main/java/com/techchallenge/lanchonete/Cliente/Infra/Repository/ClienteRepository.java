package com.techchallenge.lanchonete.Cliente.Infra.Repository;

import com.techchallenge.lanchonete.Cliente.Domain.Entity.Cliente;
import com.techchallenge.lanchonete.Cliente.Port.Repository.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepository implements ClienteRepositoryPort {
    @Override
    public void salvar(Cliente cliente) {

    }

    @Override
    public Cliente buscar(String cpf) {
        return null;
    }
}
