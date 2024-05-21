package com.techchallenge.pagamento.infrastructure.mapper.cliente;


import com.techchallenge.pagamento.application.dto.ClienteDTO;
import com.techchallenge.pagamento.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente clienteDTOtoCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }

    public ClienteDTO clienteToClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }
}
