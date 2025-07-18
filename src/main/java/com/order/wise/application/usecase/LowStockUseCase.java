package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.LowStockGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LowStockUseCase {

    private final OrderToStockDTO orderToStockDTO;
    private final LowStockGateway lowStockGateway;

    public void execute(Order order) {
        log.info("Lowering stock for the order: {}", order.getId());
        List<StockDTO> stockRequests = orderToStockDTO.execute(order);
        lowStockGateway.send(stockRequests);
    }

}
