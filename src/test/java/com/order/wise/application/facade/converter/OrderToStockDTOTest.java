package com.order.wise.application.facade.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.rabbitmq.dto.StockDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderToStockDTOUnitTest {

    @Test
    @DisplayName("Should convert Order to a list of StockDTOs correctly")
    void execute_ShouldConvertOrderToStockDTOList() {

        Order order = Instancio.create(Order.class);

        List<StockDTO> stockDTOs = new OrderToStockDTO().execute(order);

        assertThat(stockDTOs).isNotNull().hasSize(order.getOrderItems().size());

        for (int i = 0; i < stockDTOs.size(); i++) {
            OrderItem originalItem = order.getOrderItems().get(i);
            StockDTO convertedDTO = stockDTOs.get(i);

            assertThat(convertedDTO.getProdutoId()).isEqualTo(originalItem.getProductId());
            assertThat(convertedDTO.getQuantidade()).isEqualTo(originalItem.getQuantity());
            assertThat(convertedDTO.getPedidoId()).isEqualTo(order.getId());
        }
    }

}