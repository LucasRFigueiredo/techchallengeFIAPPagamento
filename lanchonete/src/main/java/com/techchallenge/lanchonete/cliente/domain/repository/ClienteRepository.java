package com.techchallenge.lanchonete.cliente.domain.repository;

import com.techchallenge.lanchonete.cliente.domain.entity.Cliente;
import com.techchallenge.lanchonete.cliente.port.repository.ClienteRepositoryPort;
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
