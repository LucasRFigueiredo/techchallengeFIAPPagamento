package com.techchallenge.lanchonete.Cliente.Domain.Mapper;

import com.techchallenge.lanchonete.Cliente.Domain.Dto.ClienteDTO;
import com.techchallenge.lanchonete.Cliente.Domain.Entity.Cliente;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {
    Cliente pessoaDTOToPessoa(ClienteDTO clienteDTO);
}
