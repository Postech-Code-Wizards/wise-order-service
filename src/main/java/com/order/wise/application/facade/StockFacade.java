package com.order.wise.application.facade;

import com.order.wise.application.usecase.PaymentUseCase;
import com.order.wise.application.usecase.UpdateStatusOrderUseCase;
import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockFacade {

    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final PaymentUseCase paymentUseCase;
    private final OrderGateway orderGateway;

    public void stockResponse(Long orderId, boolean status) {
        if(!status) {
            updateStatusOrderUseCase.execute(orderId, StatusEnum.CLOSED_OUT_OF_STOCK);
        } else {
            Order order = orderGateway.findById(orderId);
            paymentUseCase.execute(order);
        }
    }
}
