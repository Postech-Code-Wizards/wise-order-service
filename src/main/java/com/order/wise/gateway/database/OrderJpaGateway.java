package com.order.wise.gateway.database;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.database.converter.OrderConverter;
import com.order.wise.gateway.database.entities.OrderEntity;
import com.order.wise.gateway.database.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderJpaGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public Order save(Order order) {

        OrderEntity orderEntity = orderConverter.toEntity(order);
        return orderConverter.toDomain(orderRepository.save(orderEntity));

    }

    @Override
    public void updateStatus(Long id, StatusEnum status) {

        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);

    }
}
