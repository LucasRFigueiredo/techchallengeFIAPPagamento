package com.techchallenge.lanchonete.pedido.domain.mapper;

import com.techchallenge.lanchonete.pedido.domain.dto.PedidoDTO;
import com.techchallenge.lanchonete.pedido.domain.entity.Pedido;
import org.mapstruct.Mapper;

@Mapper
public interface PedidoMapper {
    Pedido pedidoDTOToPedido(PedidoDTO pedidoDTO);
}
