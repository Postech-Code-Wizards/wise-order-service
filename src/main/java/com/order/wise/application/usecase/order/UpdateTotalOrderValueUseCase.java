package com.order.wise.application.usecase.order;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateTotalOrderValueUseCase {

    private final OrderGateway orderGateway;

    public void execute(BigInteger orderId){
        Order order = orderGateway.findById(orderId);
        BigDecimal totalValue = order.getOrderItems().stream()
                .map(OrderItem::calculateSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orderGateway.updateTotalValue(orderId, totalValue);
    }
}
