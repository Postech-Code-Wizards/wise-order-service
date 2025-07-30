package com.order.wise.application.usecase.payment;

import com.order.wise.application.usecase.stock.ResetStockUseCase;
import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.PaymentGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentUseCaseUnitTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private ResetStockUseCase resetStockUseCase;

    @InjectMocks
    private PaymentUseCase paymentUseCase;

    @Test
    @DisplayName("Should process payment successfully and update order status to CLOSED_SUCCESSFULLY")
    void execute_ShouldProcessPaymentSuccessfully() {

        Order order = Instancio.create(Order.class);
        String paymentId = Instancio.create(String.class);

        when(paymentGateway.getPayment(order)).thenReturn(paymentId);
        when(orderGateway.findById(order.getId())).thenReturn(order);

        paymentUseCase.execute(order.getId());

        verify(paymentGateway).getPayment(order);
        verify(orderGateway).updatePayment(order.getId(), paymentId);
        verifyNoInteractions(resetStockUseCase);
    }

}