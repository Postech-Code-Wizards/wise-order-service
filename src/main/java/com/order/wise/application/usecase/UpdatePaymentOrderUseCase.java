package com.order.wise.application.usecase;

import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdatePaymentOrderUseCase {

    private final OrderGateway orderGateway;

    public void execute(Long orderId, String paymentId) {
        orderGateway.updatePayment(orderId, paymentId);
    }

}
