package com.techchallenge.pagamento.infrastructure.persistence.repository.produto;

import com.techchallenge.pagamento.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}

