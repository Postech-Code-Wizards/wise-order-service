package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderUseCase {

    private final CreateOrderUseCase createOrderUseCase;
    private final LowStockUseCase lowStockUseCase;
    private final PaymentUseCase paymentUseCase;
    private final OrderGateway orderGateway;

    public void execute(Order order) {
        log.info("Processing order: {}", order);
        Order orderSaved = createOrderUseCase.createOrder(order);
        lowStockUseCase.execute(orderSaved);
        Order statusAfterLowStock = orderGateway.findById(order.getId());
        if (statusAfterLowStock.getStatus() == StatusEnum.OPEN) {
            paymentUseCase.execute(orderSaved);
        }
    }
}
