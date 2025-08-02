package com.order.wise.application.usecase.stock;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.StockGateway;
import com.order.wise.gateway.rabbitmq.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LowStockUseCase {

    private final OrderToStockDTO orderToStockDTO;
    private final StockGateway stockGateway;
    private final OrderGateway orderGateway;

    public void execute(BigInteger orderId) {
        log.info("Lowering stock for the order: {}", orderId);
        Order order = orderGateway.findById(orderId);
        List<StockDTO> stockRequests = orderToStockDTO.execute(order);
        stockGateway.low(stockRequests);
    }

}
