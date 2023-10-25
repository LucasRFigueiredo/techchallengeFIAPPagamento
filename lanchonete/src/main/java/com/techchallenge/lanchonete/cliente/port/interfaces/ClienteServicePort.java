package com.techchallenge.lanchonete.cliente.port.interfaces;

import com.techchallenge.lanchonete.cliente.domain.dto.ClienteDTO;

public interface ClienteServicePort {

    void criarPessoa(ClienteDTO clienteDTO);
    //PessoaDTO buscarPessoa(String cpf) throws notfoundex;
}
