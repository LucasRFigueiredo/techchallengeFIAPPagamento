package com.techchallenge.lanchonete.cliente.domain.service;

import com.techchallenge.lanchonete.cliente.domain.dto.ClienteDTO;
import com.techchallenge.lanchonete.cliente.domain.entity.Cliente;
import com.techchallenge.lanchonete.cliente.domain.mapper.ClienteMapper;
import com.techchallenge.lanchonete.cliente.port.interfaces.ClienteServicePort;
import com.techchallenge.lanchonete.cliente.port.repository.ClienteRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClienteServiceImpl implements ClienteServicePort {
    private final ClienteRepositoryPort clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public void criarPessoa(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.pessoaDTOToPessoa(clienteDTO);
        this.clienteRepository.salvar(cliente);
    }
}
