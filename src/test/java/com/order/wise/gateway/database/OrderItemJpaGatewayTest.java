package com.order.wise.gateway.database;

import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.database.converter.OrderItemConverter;
import com.order.wise.gateway.database.entities.OrderItemEntity;
import com.order.wise.gateway.database.repositories.OrderItemRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderItemJpaGatewayTest {

    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private OrderItemConverter orderItemConverter;

    @InjectMocks
    private OrderItemJpaGateway orderItemJpaGateway;

    @Test
    @DisplayName("Should save all order items by converting them to entities and then saving")
    void saveAllTest() {
        List<OrderItem> orderItemList = Instancio.ofList(OrderItem.class).size(3).create();
        List<OrderItemEntity> orderItemEntityList = Instancio.ofList(OrderItemEntity.class).size(3).create();

        when(orderItemConverter.toEntity(eq(orderItemList), any())).thenReturn(orderItemEntityList);

        orderItemJpaGateway.saveAll(orderItemList);

        verify(orderItemConverter).toEntity(orderItemList, null);
        verify(orderItemRepository).saveAll(orderItemEntityList);
    }
}