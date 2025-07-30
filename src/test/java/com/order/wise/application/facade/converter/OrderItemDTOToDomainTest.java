package com.order.wise.application.facade.converter;

import com.order.wise.domain.OrderItem;
import com.order.wise.infrastructure.rabbitmq.dto.ProductDTO;
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
        assertThat(orderItem.getSkuProduct()).isEqualTo(productDTO.getSku());
        assertThat(orderItem.getQuantity()).isEqualTo(productDTO.getQuantity());
    }
}