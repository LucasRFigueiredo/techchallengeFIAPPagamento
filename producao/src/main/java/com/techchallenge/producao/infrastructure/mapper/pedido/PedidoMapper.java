package com.techchallenge.producao.infrastructure.mapper.pedido;

import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;
import com.techchallenge.producao.infrastructure.mapper.cliente.ClienteMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoMapper {
    private final ClienteMapper clienteMapper;
    private final ProdutoMapper produtoMapper;

    public PedidoMapper(ClienteMapper clienteMapper, ProdutoMapper produtoMapper) {
        this.clienteMapper = clienteMapper;
        this.produtoMapper = produtoMapper;
    }

    public Pedido pedidoDTOToPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setCliente(clienteMapper.clienteDTOtoCliente(pedidoDTO.getCliente()));
        pedido.setStatus(pedidoDTO.getStatus());

        List<Produto> produtos = new ArrayList<>();
        if (pedidoDTO.getItens() != null) {
            for (ProdutoDTO produtoDTO : pedidoDTO.getItens()) {
                produtos.add(produtoMapper.produtoDTOToProduto(produtoDTO));
            }
        }
        pedido.setItens(produtos);

        return pedido;
    }

    public PedidoDTO pedidoToPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setCliente(clienteMapper.clienteToClienteDTO(pedido.getCliente()));
        pedidoDTO.setStatus(pedido.getStatus());

        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        if (pedido.getItens() != null) {
            for (Produto produto : pedido.getItens()) {
                produtosDTO.add(produtoMapper.produtoToProdutoDTO(produto));
            }
        }
        pedidoDTO.setItens(produtosDTO);

        return pedidoDTO;
    }
}
