package com.order.wise.application.facade.converter;

import com.order.wise.domain.OrderItem;
import com.order.wise.infrastructure.messaging.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDTOToDomain {

    private OrderItemDTOToDomain() {
    }

    public static OrderItem execute(ProductDTO productDTO) {
        return OrderItem.builder()
                .productId(productDTO.getId())
                .skuProduct(productDTO.getSku())
                .productName(productDTO.getName())
                .unitPrice(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .subtotal(productDTO.getTotalPriceProduct())
                .build();
    }
}
