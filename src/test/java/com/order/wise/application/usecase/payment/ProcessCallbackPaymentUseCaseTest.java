package com.order.wise.application.usecase.payment;

import com.order.wise.application.usecase.stock.ResetStockUseCase;
import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessCallbackPaymentUseCaseTest {

    @Mock
    private OrderGateway orderGateway;
    @Mock
    private ResetStockUseCase resetStockUseCase;

    @InjectMocks
    private ProcessCallbackPaymentUseCase processCallbackPaymentUseCase;

    @Test
    @DisplayName("Should process successful payment and update order status to CLOSED_SUCCESSFULLY")
    void execute_successfulPaymentTest() {
        String paymentId = Instancio.create(String.class);
        boolean isSuccess = true;
        Order order = Instancio.create(Order.class);

        when(orderGateway.findByPaymentId(paymentId)).thenReturn(order);

        processCallbackPaymentUseCase.execute(paymentId, isSuccess);

        verify(orderGateway).findByPaymentId(paymentId);
        verify(orderGateway).updateStatus(order.getId(), StatusEnum.CLOSED_SUCCESSFULLY);
    }

    @Test
    @DisplayName("Should process failed payment, update order status to CLOSED_NO_CREDIT, and reset stock")
    void execute_failedPaymentTest() {
        String paymentId = Instancio.create(String.class);
        boolean isSuccess = false;
        Order order = Instancio.create(Order.class);

        when(orderGateway.findByPaymentId(paymentId)).thenReturn(order);

        processCallbackPaymentUseCase.execute(paymentId, isSuccess);

        verify(orderGateway).findByPaymentId(paymentId);
        verify(orderGateway).updateStatus(order.getId(), StatusEnum.CLOSED_NO_CREDIT);
        verify(resetStockUseCase).execute(order);
    }

}