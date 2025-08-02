package com.order.wise.gateway.database;

import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.OrderItemGateway;
import com.order.wise.gateway.database.converter.OrderItemConverter;
import com.order.wise.gateway.database.entities.OrderItemEntity;
import com.order.wise.gateway.database.repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemJpaGateway implements OrderItemGateway {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemConverter orderItemConverter;

    @Override
    public void saveAll(List<OrderItem> orderItemList) {
        List<OrderItemEntity> orderItemEntityList = orderItemConverter.toEntity(orderItemList, null);
        orderItemRepository.saveAll(orderItemEntityList);
    }

}
