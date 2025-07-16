package com.order.wise.application.facade.converter;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.infrastructure.messaging.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoDTOToDomain {

    private ItemPedidoDTOToDomain() {
    }

    public static ItensPedidos execute(ProductDTO productDTO) {
        return ItensPedidos.builder()
                .produtoId(productDTO.getId())
                .nomeProduto(productDTO.getName())
                .precoUnitario(productDTO.getPrice())
                .quantidade(productDTO.getQuantity())
                .subtotal(productDTO.getTotalPriceProduct())
                .build();
    }
}
