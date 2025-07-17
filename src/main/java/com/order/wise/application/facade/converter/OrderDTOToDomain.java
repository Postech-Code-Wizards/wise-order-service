package com.order.wise.application.facade.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class OrderDTOToDomain {

    public Order execute(OrderDTO orderDTO) {
        return Order.builder()
                .totalValue(orderDTO.getTotalPrice())
                .clientId(orderDTO.getClient().getId().longValue())
                .orderItems(orderDTO.getProductList().stream()
                        .map(OrderItemDTOToDomain::execute)
                        .toList())
                .creditCardNumber(orderDTO.getPaymentMethod().getCardNumber())
                .status(StatusEnum.OPEN)
                .dateCreated(ZonedDateTime.now())
                .build();
    }
}
