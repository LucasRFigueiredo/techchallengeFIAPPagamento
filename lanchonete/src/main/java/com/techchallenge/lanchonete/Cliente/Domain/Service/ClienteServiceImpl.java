package com.techchallenge.lanchonete.Cliente.Domain.Service;

import com.techchallenge.lanchonete.Cliente.Domain.Dto.ClienteDTO;
import com.techchallenge.lanchonete.Cliente.Domain.Entity.Cliente;
import com.techchallenge.lanchonete.Cliente.Domain.Mapper.ClienteMapper;
import com.techchallenge.lanchonete.Cliente.Port.Interface.ClienteServicePort;
import com.techchallenge.lanchonete.Cliente.Port.Repository.ClienteRepositoryPort;
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
