package com.order.wise.gateway.database.repositories;

import com.order.wise.gateway.database.entities.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, BigInteger> {

    Optional<OrderEntity> findByPaymentId(String paymentId);

    @Modifying
    @Transactional
    @Query("UPDATE OrderEntity SET clientId = :clientId WHERE id = :orderId")
    void updateClientId(@Param("orderId") BigInteger orderId, @Param("clientId") BigInteger clientId);

    @Modifying
    @Transactional
    @Query("UPDATE OrderEntity SET totalValue = :totalValue WHERE id = :orderId")
    void updateTotalValue(@Param("orderId") BigInteger orderId, @Param("totalValue")BigDecimal totalValue);

}