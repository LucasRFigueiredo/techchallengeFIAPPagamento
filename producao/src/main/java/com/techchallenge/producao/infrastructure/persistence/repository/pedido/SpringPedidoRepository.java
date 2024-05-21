package com.techchallenge.producao.infrastructure.persistence.repository.pedido;

import com.techchallenge.producao.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringPedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
