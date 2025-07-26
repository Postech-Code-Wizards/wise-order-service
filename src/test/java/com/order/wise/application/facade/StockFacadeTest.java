package com.order.wise.application.facade;

import com.order.wise.application.usecase.PaymentUseCase;
import com.order.wise.application.usecase.UpdateStatusOrderUseCase;
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
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockFacadeUnitTest {

    @Mock
    private UpdateStatusOrderUseCase updateStatusOrderUseCase;

    @Mock
    private PaymentUseCase paymentUseCase;

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private StockFacade stockFacade;

    @Test
    @DisplayName("Should update order status to CLOSED_OUT_OF_STOCK when status is false")
    void stockResponse_ShouldUpdateStatusWhenStatusIsFalse() {

        Long orderId = Instancio.create(Long.class);
        boolean status = false;

        stockFacade.stockResponse(orderId, status);

        verify(updateStatusOrderUseCase).execute(orderId, StatusEnum.CLOSED_OUT_OF_STOCK);
        verifyNoInteractions(orderGateway);
        verifyNoInteractions(paymentUseCase);
    }

    @Test
    @DisplayName("Should process payment when status is true")
    void stockResponse_ShouldProcessPaymentWhenStatusIsTrue() {

        Long orderId = Instancio.create(Long.class);
        boolean status = true;
        Order order = Instancio.create(Order.class);

        when(orderGateway.findById(orderId)).thenReturn(order);

        stockFacade.stockResponse(orderId, status);

        verify(orderGateway).findById(orderId);
        verify(paymentUseCase).execute(order);
        verifyNoInteractions(updateStatusOrderUseCase);
    }
}