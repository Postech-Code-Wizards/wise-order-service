package com.order.wise.application.usecase.payment;

import com.order.wise.application.usecase.stock.ResetStockUseCase;
import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessCallbackPaymentUseCase {

    private final OrderGateway orderGateway;
    private final ResetStockUseCase resetStockUseCase;

    public void execute(String paymentId, boolean isSuccess){
        Order order = orderGateway.findByPaymentId(paymentId);
        if(!isSuccess) {
            log.info("Payment failed for order: {}", order.getId());
            orderGateway.updateStatus(order.getId(), StatusEnum.CLOSED_NO_CREDIT);
            resetStockUseCase.execute(order);
        } else {
            log.info("Payment successful for order: {}", order.getId());
            orderGateway.updateStatus(order.getId(), StatusEnum.CLOSED_SUCCESSFULLY);
        }

    }
}
