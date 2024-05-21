package com.techchallenge.producao.infrastructure.mapper.pedido;

import com.techchallenge.producao.application.dto.ClienteDTO;
import com.techchallenge.producao.application.dto.PedidoDTO;
import com.techchallenge.producao.application.dto.ProdutoDTO;
import com.techchallenge.producao.domain.Cliente;
import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.domain.Produto;
import com.techchallenge.producao.infrastructure.mapper.cliente.ClienteMapper;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoMapper;
import com.techchallenge.producao.infrastructure.mapper.produto.ProdutoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoMapperTest {

    private PedidoMapper pedidoMapper;
    private ClienteMapper clienteMapper;
    private ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        clienteMapper = new ClienteMapper();
        produtoMapper = new ProdutoMapper();
        pedidoMapper = new PedidoMapper(clienteMapper, produtoMapper);
    }

    @Test
    void pedidoDTOToPedido() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(1L);
        pedidoDTO.setStatus("Entregue");
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId("1");
        pedidoDTO.setCliente(clienteDTO);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Hambúrguer");
        produtoDTO.setDescricao("Hambúrguer delicioso");
        produtoDTOs.add(produtoDTO);
        pedidoDTO.setItens(produtoDTOs);

        Pedido pedido = pedidoMapper.pedidoDTOToPedido(pedidoDTO);

        assertEquals(pedidoDTO.getId(), pedido.getId());
        assertEquals(pedidoDTO.getStatus(), pedido.getStatus());
        assertEquals(pedidoDTO.getCliente().getId(), pedido.getCliente().getId().toString());
        assertEquals(pedidoDTO.getItens().size(), pedido.getItens().size());
    }

    @Test
    void pedidoToPedidoDTO() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus("Entregue");
        Cliente cliente = new Cliente();
        cliente.setId("664c22ed062528092ab45298");
        pedido.setCliente(cliente);
        List<Produto> produtos = new ArrayList<>();
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Hambúrguer");
        produto.setDescricao("Hambúrguer delicioso");
        produtos.add(produto);
        pedido.setItens(produtos);
        PedidoDTO pedidoDTO = pedidoMapper.pedidoToPedidoDTO(pedido);

        assertEquals(pedido.getId(), pedidoDTO.getId());
        assertEquals(pedido.getStatus(), pedidoDTO.getStatus());
        assertEquals(pedido.getCliente().getId().toString(), pedidoDTO.getCliente().getId());
        assertEquals(pedido.getItens().size(), pedidoDTO.getItens().size());
    }
}
