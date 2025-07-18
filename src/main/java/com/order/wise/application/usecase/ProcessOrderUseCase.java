package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderUseCase {

    private final CreateOrderUseCase createOrderUseCase;
    private final LowStockUseCase lowStockUseCase;

    public void execute(Order order) {
        log.info("Processing order: {}", order);
        Order orderSaved = createOrderUseCase.createOrder(order);
        lowStockUseCase.execute(orderSaved);
    }
}
