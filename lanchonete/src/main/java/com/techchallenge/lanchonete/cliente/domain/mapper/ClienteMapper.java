package com.techchallenge.lanchonete.cliente.domain.mapper;

import com.techchallenge.lanchonete.cliente.domain.dto.ClienteDTO;
import com.techchallenge.lanchonete.cliente.domain.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper
public interface ClienteMapper {
    Cliente pessoaDTOToPessoa(ClienteDTO clienteDTO);
}
