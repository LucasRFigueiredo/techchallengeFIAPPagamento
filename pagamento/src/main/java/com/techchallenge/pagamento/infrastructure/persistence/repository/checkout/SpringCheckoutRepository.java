package com.techchallenge.pagamento.infrastructure.persistence.repository.checkout;

import com.techchallenge.pagamento.infrastructure.persistence.entity.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringCheckoutRepository extends JpaRepository<CheckoutEntity, Long> {
    @Query("SELECT c FROM CheckoutEntity c WHERE c.pagamento = :statusPagamento")
    List<CheckoutEntity> findByStatusPagamento(@Param("statusPagamento") String statusPagamento);
}
