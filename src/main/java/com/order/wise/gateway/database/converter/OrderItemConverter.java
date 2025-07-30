package com.order.wise.gateway.database.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.database.entities.OrderEntity;
import com.order.wise.gateway.database.entities.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemConverter {

    public List<OrderItemEntity> toEntity(List<OrderItem> orderItems, OrderEntity orderEntity) {

        return orderItems
                .stream()
                .map(items -> new OrderItemEntity(
                        items.getId(),
                        orderEntity,
                        items.getProductId(),
                        items.getSkuProduct(),
                        items.getProductName(),
                        items.getQuantity(),
                        items.getUnitPrice()
                )).toList();
    }

    public List<OrderItem> toDomain(List<OrderItemEntity> orderItemEntities, Order order) {

        return orderItemEntities
                .stream()
                .map(p -> new OrderItem(
                        p.getId(),
                        order,
                        p.getProductId(),
                        p.getSkuProduct(),
                        p.getProductName(),
                        p.getQuantity(),
                        p.getUnitPrice()
                )).toList();

    }

}
