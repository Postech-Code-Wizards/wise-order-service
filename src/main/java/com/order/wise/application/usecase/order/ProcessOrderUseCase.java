package com.order.wise.application.usecase.order;

import com.order.wise.application.usecase.product.ProcessOrderItemProductAssignmentUseCase;
import com.order.wise.application.usecase.payment.PaymentUseCase;
import com.order.wise.application.usecase.client.ProcessOrderClientAssignmentUseCase;
import com.order.wise.application.usecase.stock.LowStockUseCase;
import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProcessOrderClientAssignmentUseCase processOrderClientAssignmentUseCase;
    private final ProcessOrderItemProductAssignmentUseCase processOrderProductAssignmentUseCase;
    private final LowStockUseCase lowStockUseCase;
    private final UpdateTotalOrderValueUseCase updateTotalOrderValueUseCase;
    private final PaymentUseCase paymentUseCase;

    public void execute(Order order) {
        log.info("Processing order: {}", order);
        Order orderSaved = orderGateway.save(order);
        processOrderClientAssignmentUseCase.execute(orderSaved);
        processOrderProductAssignmentUseCase.execute(orderSaved, orderSaved.getOrderItems());
        updateTotalOrderValueUseCase.execute(orderSaved.getId());
        paymentUseCase.execute(orderSaved.getId());
        lowStockUseCase.execute(orderSaved.getId());
    }

}
