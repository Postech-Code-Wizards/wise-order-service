package com.order.wise.application.usecase.order;

import com.order.wise.application.usecase.client.ProcessOrderClientAssignmentUseCase;
import com.order.wise.application.usecase.payment.PaymentUseCase;
import com.order.wise.application.usecase.product.ProcessOrderItemProductAssignmentUseCase;
import com.order.wise.application.usecase.stock.LowStockUseCase;
import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;
    @Mock
    private ProcessOrderClientAssignmentUseCase processOrderClientAssignmentUseCase;
    @Mock
    private ProcessOrderItemProductAssignmentUseCase processOrderProductAssignmentUseCase;
    @Mock
    private LowStockUseCase lowStockUseCase;
    @Mock
    private UpdateTotalOrderValueUseCase updateTotalOrderValueUseCase;
    @Mock
    private PaymentUseCase paymentUseCase;

    @InjectMocks
    private ProcessOrderUseCase processOrderUseCase;

    @Test
    @DisplayName("Should process an order successfully")
    void executeTest() {
        Order order = Instancio.create(Order.class);
        Order savedOrder = Instancio.create(Order.class);

        when(orderGateway.save(any(Order.class))).thenReturn(savedOrder);

        processOrderUseCase.execute(order);

        verify(orderGateway).save(order);
        verify(processOrderClientAssignmentUseCase).execute(savedOrder);
        verify(processOrderProductAssignmentUseCase).execute(savedOrder, savedOrder.getOrderItems());
        verify(updateTotalOrderValueUseCase).execute(savedOrder.getId());
        verify(paymentUseCase).execute(savedOrder.getId());
        verify(lowStockUseCase).execute(savedOrder.getId());
    }
}