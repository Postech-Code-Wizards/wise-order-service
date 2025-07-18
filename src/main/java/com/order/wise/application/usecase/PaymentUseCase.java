package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final UpdatePaymentOrderUseCase updatePaymentOrderUseCase;
    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final ResetStockUseCase resetStockUseCase;

    public void execute(Order order) {
        log.info("Processing payment for order: {} with amount: {}", order.getId(), order.getTotalValue());
        String paymentId = paymentGateway.getPayment(order);
        if (paymentId != null) {
            log.info("Payment successful for order: {} with payment ID: {}", order.getId(), paymentId);
            updatePaymentOrderUseCase.execute(order.getId(), paymentId);
            updateStatusOrderUseCase.execute(order.getId(), StatusEnum.CLOSED_SUCCESSFULLY);
        } else {
            log.error("Payment failed for order: {}", order.getId());
            updateStatusOrderUseCase.execute(order.getId(), StatusEnum.CLOSED_NO_CREDIT);
            resetStockUseCase.execute(order);
        }
    }
}
