package com.order.wise.application.usecase.payment;

import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final OrderGateway orderGateway;

    public void execute(BigInteger orderId) {
        Order order = orderGateway.findById(orderId);
        String paymentId = paymentGateway.getPayment(order);
        orderGateway.updatePayment(order.getId(), paymentId);
    }
}
