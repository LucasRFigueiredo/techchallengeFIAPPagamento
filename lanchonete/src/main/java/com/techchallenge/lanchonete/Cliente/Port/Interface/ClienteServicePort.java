package com.techchallenge.lanchonete.Cliente.Port.Interface;

import com.techchallenge.lanchonete.Cliente.Domain.Dto.ClienteDTO;

public interface ClienteServicePort {

    void criarPessoa(ClienteDTO clienteDTO);
    //PessoaDTO buscarPessoa(String cpf) throws notfoundex;
}
