package com.order.wise.application.facade.converter;

import com.order.wise.domain.OrderItem;
import com.order.wise.infrastructure.rabbitmq.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDTOToDomain {

    private OrderItemDTOToDomain() {
    }

    public static OrderItem execute(ProductDTO productDTO) {

        return new OrderItem(null,
                null,
                null,
                productDTO.getSku(),
                null,
                productDTO.getQuantity(),
                null
        );
    }
}
