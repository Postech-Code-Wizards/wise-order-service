package com.order.wise.gateway.database.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.database.entities.OrderEntity;
import com.order.wise.gateway.database.entities.OrderItemEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderItemConverterUnitTest {

    private final OrderItemConverter orderItemConverter = new OrderItemConverter();

    @Test
    @DisplayName("Should convert list of OrderItem domain objects to OrderItemEntity list correctly")
    void toEntity_ShouldConvertOrderItemListToOrderItemEntityList() {

        List<OrderItem> orderItems = Instancio.ofList(OrderItem.class).size(3).create();
        OrderEntity orderEntity = Instancio.create(OrderEntity.class);

        List<OrderItemEntity> orderItemEntities = orderItemConverter.toEntity(orderItems, orderEntity);

        assertThat(orderItemEntities).isNotNull().hasSize(orderItems.size());
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem originalItem = orderItems.get(i);
            OrderItemEntity convertedEntity = orderItemEntities.get(i);

            assertThat(convertedEntity.getOrderEntity()).isEqualTo(orderEntity);
            assertThat(convertedEntity.getProductId()).isEqualTo(originalItem.getProductId());
            assertThat(convertedEntity.getSkuProduct()).isEqualTo(originalItem.getSkuProduct());
            assertThat(convertedEntity.getProductName()).isEqualTo(originalItem.getProductName());
            assertThat(convertedEntity.getQuantity()).isEqualTo(originalItem.getQuantity());
            assertThat(convertedEntity.getUnitPrice()).isEqualTo(originalItem.getUnitPrice());
            assertThat(convertedEntity.getSubtotal()).isEqualTo(originalItem.getSubtotal());
        }
    }

    @Test
    @DisplayName("Should return empty list when converting empty OrderItem list to OrderItemEntity list")
    void toEntity_ShouldReturnEmptyListWhenOrderItemListIsEmpty() {

        List<OrderItem> orderItems = List.of();
        OrderEntity orderEntity = Instancio.create(OrderEntity.class);

        List<OrderItemEntity> orderItemEntities = orderItemConverter.toEntity(orderItems, orderEntity);

        assertThat(orderItemEntities).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Should convert list of OrderItemEntity to OrderItem domain object list correctly")
    void toDomain_ShouldConvertOrderItemEntityListToOrderItemList() {

        List<OrderItemEntity> orderItemEntities = Instancio.ofList(OrderItemEntity.class).size(3).create();
        Order associatedOrder = Instancio.create(Order.class); // Pass a dummy Order, though it's often null in usage

        List<OrderItem> orderItems = orderItemConverter.toDomain(orderItemEntities, associatedOrder);

        assertThat(orderItems).isNotNull().hasSize(orderItemEntities.size());
        for (int i = 0; i < orderItemEntities.size(); i++) {
            OrderItemEntity originalEntity = orderItemEntities.get(i);
            OrderItem convertedItem = orderItems.get(i);

            assertThat(convertedItem.getId()).isEqualTo(originalEntity.getId());
            assertThat(convertedItem.getOrder()).isEqualTo(associatedOrder); // Should be the same dummy order
            assertThat(convertedItem.getProductId()).isEqualTo(originalEntity.getProductId());
            assertThat(convertedItem.getSkuProduct()).isEqualTo(originalEntity.getSkuProduct());
            assertThat(convertedItem.getProductName()).isEqualTo(originalEntity.getProductName());
            assertThat(convertedItem.getQuantity()).isEqualTo(originalEntity.getQuantity());
            assertThat(convertedItem.getUnitPrice()).isEqualTo(originalEntity.getUnitPrice());
            assertThat(convertedItem.getSubtotal()).isEqualTo(originalEntity.getSubtotal());
        }
    }

    @Test
    @DisplayName("Should return empty list when converting empty OrderItemEntity list to OrderItem list")
    void toDomain_ShouldReturnEmptyListWhenOrderItemEntityListIsEmpty() {

        List<OrderItemEntity> orderItemEntities = List.of();
        Order associatedOrder = Instancio.create(Order.class);

        List<OrderItem> orderItems = orderItemConverter.toDomain(orderItemEntities, associatedOrder);

        assertThat(orderItems).isNotNull().isEmpty();
    }
}