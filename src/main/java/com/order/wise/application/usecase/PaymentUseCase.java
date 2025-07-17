package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.OrderToPaymentDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.PaymentGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentUseCase {

    private final OrderToPaymentDTO orderToPaymentDTO;
    private final PaymentGateway paymentGateway;

    public void execute(Order order) {
        log.info("Processing payment for order: {} with amount: {}", order.getId(), order.getTotalValue());
        PaymentDTO paymentRequest = orderToPaymentDTO.execute(order);
        paymentGateway.send(paymentRequest);
    }
}
