package com.order.wise.application.facade.converter;

import com.order.wise.domain.Order;
import com.order.wise.gateway.rabbitmq.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderToStockDTO {

    public List<StockDTO> execute(Order order) {

        return order.getOrderItems().stream()
                .map(item -> new StockDTO(
                        item.getProductId(),
                        item.getQuantity(),
                        order.getId()
                ))
                .toList();
    }

}
