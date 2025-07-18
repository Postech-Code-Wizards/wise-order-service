package com.order.wise.gateway.database.converter;

import com.order.wise.domain.Order;
import com.order.wise.gateway.database.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderEntity toEntity(Order order) {

        if (order == null) return null;

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setClientId(order.getClientId());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setDateCreated(order.getDateCreated());
        orderEntity.setCreditCard(order.getCreditCardNumber());
        orderEntity.setPaymentId(order.getPaymentId());
        orderEntity.setTotalValue(order.getTotalValue());
        orderEntity.setOrderItemEntities(orderItemConverter.toEntity(order.getOrderItems(), orderEntity));

        return orderEntity;

    }

    public Order toDomain(OrderEntity orderEntity) {
        if (orderEntity == null) return null;

        return Order.builder()
                .id(orderEntity.getId())
                .clientId(orderEntity.getClientId())
                .status(orderEntity.getStatus())
                .dateCreated(orderEntity.getDateCreated())
                .creditCardNumber(orderEntity.getCreditCard())
                .paymentId(orderEntity.getPaymentId())
                .totalValue(orderEntity.getTotalValue())
                .orderItems(orderItemConverter.toDomain(orderEntity.getOrderItemEntities(), null))
                .build();

    }

}
