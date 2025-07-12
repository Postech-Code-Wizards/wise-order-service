package com.order.wise.gateway.database.repositories;

import com.order.wise.gateway.database.entities.ItensPedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidosRepository extends JpaRepository<ItensPedidosEntity, Long> {
}
