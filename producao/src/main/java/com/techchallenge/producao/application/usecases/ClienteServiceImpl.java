package com.techchallenge.producao.application.usecases;

import com.techchallenge.producao.application.dto.ClienteDTO;
import com.techchallenge.producao.domain.Cliente;
import com.techchallenge.producao.infrastructure.mapper.cliente.ClienteEntityMapper;
import com.techchallenge.producao.infrastructure.mapper.cliente.ClienteMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.ClienteEntity;
import com.techchallenge.producao.infrastructure.persistence.repository.cliente.SpringClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl {

    private final SpringClienteRepository springClienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteEntityMapper clienteEntityMapper;

    public ClienteServiceImpl(SpringClienteRepository springClienteRepository, ClienteMapper clienteMapper, ClienteEntityMapper clienteEntityMapper) {
        this.springClienteRepository = springClienteRepository;
        this.clienteMapper = clienteMapper;
        this.clienteEntityMapper = clienteEntityMapper;
    }

    public void createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.clienteDTOtoCliente(clienteDTO);
        ClienteEntity clienteEntity = clienteEntityMapper.clienteToClienteEntity(cliente);
        springClienteRepository.save(clienteEntity);
    }

    public void updateCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.clienteDTOtoCliente(clienteDTO);
        ClienteEntity clienteEntity = clienteEntityMapper.clienteToClienteEntity(cliente);
        springClienteRepository.findById(clienteEntity.getId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        springClienteRepository.save(clienteEntity);
    }

    public void deleteCliente(Long id) {
        springClienteRepository.deleteById(id);
    }
}
