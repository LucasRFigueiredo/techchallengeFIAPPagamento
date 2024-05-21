package com.techchallenge.producao.infrastructure.mapper.cliente;

import com.techchallenge.producao.domain.Cliente;
import com.techchallenge.producao.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntityMapper {
    public Cliente clienteEntityToCliente(ClienteEntity clienteEntity) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteEntity.getId());
        cliente.setNome(clienteEntity.getNome());
        cliente.setCpf(clienteEntity.getCpf());
        cliente.setEmail(clienteEntity.getEmail());
        return cliente;
    }

    public ClienteEntity clienteToClienteEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(cliente.getId());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setCpf(cliente.getCpf());
        clienteEntity.setEmail(cliente.getEmail());
        return clienteEntity;
    }
}
