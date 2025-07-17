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
                .map(items -> {
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setOrderEntity(orderEntity);
                    orderItemEntity.setProductId(items.getProductId());
                    orderItemEntity.setSkuProduct(items.getSkuProduct());
                    orderItemEntity.setProductName(items.getProductName());
                    orderItemEntity.setQuantity(items.getQuantity());
                    orderItemEntity.setUnitPrice(items.getUnitPrice());
                    orderItemEntity.setSubtotal(items.getSubtotal());
                    return orderItemEntity;
                }).toList();
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
                        p.getUnitPrice(),
                        p.getSubtotal()
                )).toList();

    }

}
