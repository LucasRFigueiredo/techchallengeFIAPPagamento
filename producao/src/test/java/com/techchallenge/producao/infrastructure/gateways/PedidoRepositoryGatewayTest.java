package com.techchallenge.producao.infrastructure.gateways;

import com.techchallenge.producao.domain.Pedido;
import com.techchallenge.producao.infrastructure.mapper.pedido.PedidoEntityMapper;
import com.techchallenge.producao.infrastructure.persistence.entity.PedidoEntity;
import com.techchallenge.producao.infrastructure.persistence.repository.pedido.SpringPedidoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoRepositoryGatewayTest {

    @Mock
    private SpringPedidoRepository springPedidoRepository;

    @Mock
    private PedidoEntityMapper pedidoEntityMapper;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private PedidoRepositoryGateway pedidoRepositoryGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriar() {
        // Mock de um pedido
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        // Mock de um pedido entity
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(1L);

        // Configuração do comportamento dos mocks
        when(pedidoEntityMapper.pedidoToPedidoEntity(any(Pedido.class))).thenReturn(pedidoEntity);
        when(springPedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedidoEntity));

        // Execução do método a ser testado
        Long id = pedidoRepositoryGateway.criar(pedido);

        // Verificação se o método save do repository foi chamado
        verify(springPedidoRepository, times(1)).save(any(PedidoEntity.class));
        // Verificação se o ID retornado está correto
        assertEquals(1L, id);
    }

    @Test
    public void testListar() {
        // Mock de uma lista de pedidos entities
        List<PedidoEntity> pedidoEntities = Collections.singletonList(new PedidoEntity());

        // Mock de uma lista de pedidos DTO
        List<Pedido> pedidos = Collections.singletonList(new Pedido());

        // Configuração do comportamento dos mocks
        when(springPedidoRepository.findAll()).thenReturn(pedidoEntities);
        when(pedidoEntityMapper.pedidoEntityToPedido(any(PedidoEntity.class))).thenReturn(new Pedido());

        // Execução do método a ser testado
        List<Pedido> resultado = pedidoRepositoryGateway.listar();

        // Verificação se a lista de pedidos foi retornada corretamente
        assertEquals(pedidos, resultado);
    }
}
