package com.techchallenge.lanchonete.Pedido.Domain.Mapper;

import com.techchallenge.lanchonete.Pedido.Domain.Dto.PedidoDTO;
import com.techchallenge.lanchonete.Pedido.Domain.Entity.Pedido;
import org.mapstruct.Mapper;

@Mapper
public interface PedidoMapper {
    Pedido pedidoDTOToPedido(PedidoDTO pedidoDTO);
}
