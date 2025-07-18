package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    private final OrderGateway orderGateway;

    @Transactional
    public Order createOrder(Order order) {
        log.info("Creating order: {}", order);
        return orderGateway.save(order);
    }
}
