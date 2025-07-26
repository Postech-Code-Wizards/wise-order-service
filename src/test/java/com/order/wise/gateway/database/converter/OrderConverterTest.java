package com.order.wise.gateway.database.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.database.entities.OrderEntity;
import com.order.wise.gateway.database.entities.OrderItemEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderConverterUnitTest {

    @Mock
    private OrderItemConverter orderItemConverter;

    @InjectMocks
    private OrderConverter orderConverter;

    @Test
    @DisplayName("Should convert Order domain object to OrderEntity correctly")
    void toEntity_ShouldConvertOrderToOrderEntity() {

        Order order = Instancio.create(Order.class);
        List<OrderItemEntity> expectedOrderItemEntities = Instancio.ofList(OrderItemEntity.class)
                .size(order.getOrderItems().size())
                .create();

        when(orderItemConverter.toEntity(eq(order.getOrderItems()), any(OrderEntity.class)))
                .thenReturn(expectedOrderItemEntities);

        OrderEntity orderEntity = orderConverter.toEntity(order);

        assertThat(orderEntity).isNotNull();
        assertThat(orderEntity.getId()).isEqualTo(order.getId());
        assertThat(orderEntity.getClientId()).isEqualTo(order.getClientId());
        assertThat(orderEntity.getStatus()).isEqualTo(order.getStatus());
        assertThat(orderEntity.getDateCreated()).isEqualTo(order.getDateCreated());
        assertThat(orderEntity.getCreditCard()).isEqualTo(order.getCreditCardNumber());
        assertThat(orderEntity.getPaymentId()).isEqualTo(order.getPaymentId());
        assertThat(orderEntity.getTotalValue()).isEqualTo(order.getTotalValue());
        assertThat(orderEntity.getOrderItemEntities()).isEqualTo(expectedOrderItemEntities);
    }

    @Test
    @DisplayName("Should return null when converting a null Order to OrderEntity")
    void toEntity_ShouldReturnNullWhenOrderIsNull() {

        OrderEntity orderEntity = orderConverter.toEntity(null);

        assertThat(orderEntity).isNull();
    }

    @Test
    @DisplayName("Should convert OrderEntity to Order domain object correctly")
    void toDomain_ShouldConvertOrderEntityToOrder() {

        OrderEntity orderEntity = Instancio.create(OrderEntity.class);
        List<OrderItem> expectedOrderItems = Instancio.ofList(OrderItem.class)
                .size(orderEntity.getOrderItemEntities().size())
                .create();

        when(orderItemConverter.toDomain(orderEntity.getOrderItemEntities(), null))
                .thenReturn(expectedOrderItems);

        Order order = orderConverter.toDomain(orderEntity);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(orderEntity.getId());
        assertThat(order.getClientId()).isEqualTo(orderEntity.getClientId());
        assertThat(order.getStatus()).isEqualTo(orderEntity.getStatus());
        assertThat(order.getDateCreated()).isEqualTo(orderEntity.getDateCreated());
        assertThat(order.getCreditCardNumber()).isEqualTo(orderEntity.getCreditCard());
        assertThat(order.getPaymentId()).isEqualTo(orderEntity.getPaymentId());
        assertThat(order.getTotalValue()).isEqualTo(orderEntity.getTotalValue());
        assertThat(order.getOrderItems()).isEqualTo(expectedOrderItems);
    }

    @Test
    @DisplayName("Should return null when converting a null OrderEntity to Order domain object")
    void toDomain_ShouldReturnNullWhenOrderEntityIsNull() {

        Order order = orderConverter.toDomain(null);

        assertThat(order).isNull();
    }
}