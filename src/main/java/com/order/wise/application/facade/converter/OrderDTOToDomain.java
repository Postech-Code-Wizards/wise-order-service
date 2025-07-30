package com.order.wise.application.facade.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.infrastructure.rabbitmq.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class OrderDTOToDomain {

    public Order execute(OrderDTO orderDTO) {

        if (orderDTO == null) {
            return null;
        }

        List<OrderItem> orderItems = orderDTO.getProductList().stream()
                .map(OrderItemDTOToDomain::execute)
                .toList();

        return new Order(
                null,
                null,
                orderDTO.getClient().getIdentifier(),
                ZonedDateTime.now(),
                StatusEnum.OPEN,
                orderDTO.getPaymentMethod().getCardNumber(),
                null,
                null,
                orderItems
        );

    }
}
