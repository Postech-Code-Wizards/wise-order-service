package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.ResetStockGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResetStockUseCase {

    private final OrderToStockDTO orderToStockDTO;
    private final ResetStockGateway resetStockGateway;

    public void execute(Order order) {
        log.info("Restoring order: {}", order.getId());
        List<StockDTO> stockRequests = orderToStockDTO.execute(order);
        resetStockGateway.send(stockRequests);
    }
}
