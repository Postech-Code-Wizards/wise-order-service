package com.order.wise.application.facade.converter;

import com.order.wise.domain.OrderItem;
import com.order.wise.infrastructure.messaging.dto.ProductDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderItemDTOToDomainUnitTest {

    @Test
    @DisplayName("Should convert ProductDTO to OrderItem domain object correctly")
    void execute_ShouldConvertProductDTOToOrderItem() {

        ProductDTO productDTO = Instancio.create(ProductDTO.class);

        OrderItem orderItem = OrderItemDTOToDomain.execute(productDTO);

        assertThat(orderItem).isNotNull();
        assertThat(orderItem.getProductId()).isEqualTo(productDTO.getId());
        assertThat(orderItem.getSkuProduct()).isEqualTo(productDTO.getSku());
        assertThat(orderItem.getProductName()).isEqualTo(productDTO.getName());
        assertThat(orderItem.getUnitPrice()).isEqualTo(productDTO.getPrice());
        assertThat(orderItem.getQuantity()).isEqualTo(productDTO.getQuantity());
        assertThat(orderItem.getSubtotal()).isEqualTo(productDTO.getTotalPriceProduct());
    }
}