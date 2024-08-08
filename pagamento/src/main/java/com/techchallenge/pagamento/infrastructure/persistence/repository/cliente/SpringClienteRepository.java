package com.techchallenge.pagamento.infrastructure.persistence.repository.cliente;

import com.techchallenge.pagamento.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
