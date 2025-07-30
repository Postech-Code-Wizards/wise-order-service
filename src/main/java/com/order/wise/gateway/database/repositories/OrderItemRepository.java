package com.order.wise.gateway.database.repositories;

import com.order.wise.gateway.database.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, BigInteger> {

}