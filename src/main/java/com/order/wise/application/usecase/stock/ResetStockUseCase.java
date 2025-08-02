package com.order.wise.application.usecase.stock;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.StockGateway;
import com.order.wise.gateway.rabbitmq.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResetStockUseCase {

    private final OrderToStockDTO orderToStockDTO;
    private final StockGateway stockGateway;

    public void execute(Order order) {
        log.info("Restoring order: {}", order.getId());
        List<StockDTO> stockRequests = orderToStockDTO.execute(order);
        stockGateway.reset(stockRequests);
    }
}
