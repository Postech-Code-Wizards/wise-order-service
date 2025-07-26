package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.PaymentGateway;
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
class PaymentUseCaseUnitTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private UpdatePaymentOrderUseCase updatePaymentOrderUseCase;

    @Mock
    private UpdateStatusOrderUseCase updateStatusOrderUseCase;

    @Mock
    private ResetStockUseCase resetStockUseCase;

    @InjectMocks
    private PaymentUseCase paymentUseCase;

    @Test
    @DisplayName("Should process payment successfully and update order status to CLOSED_SUCCESSFULLY")
    void execute_ShouldProcessPaymentSuccessfully() {
        // Given an order and a successful payment ID
        Order order = Instancio.create(Order.class);
        String paymentId = Instancio.create(String.class);

        // Configure the mock PaymentGateway to return a payment ID
        when(paymentGateway.getPayment(order)).thenReturn(paymentId);

        // When the execute method is called
        paymentUseCase.execute(order);

        // Then verify interactions for a successful payment
        verify(paymentGateway).getPayment(order);
        verify(updatePaymentOrderUseCase).execute(order.getId(), paymentId);
        verify(updateStatusOrderUseCase).execute(order.getId(), StatusEnum.CLOSED_SUCCESSFULLY);
        verifyNoInteractions(resetStockUseCase); // Ensure resetStockUseCase is not called
    }

    @Test
    @DisplayName("Should handle failed payment and update order status to CLOSED_NO_CREDIT and reset stock")
    void execute_ShouldHandleFailedPayment() {
        // Given an order and a failed payment (null payment ID)
        Order order = Instancio.create(Order.class);

        // Configure the mock PaymentGateway to return null
        when(paymentGateway.getPayment(order)).thenReturn(null);

        // When the execute method is called
        paymentUseCase.execute(order);

        // Then verify interactions for a failed payment
        verify(paymentGateway).getPayment(order);
        verify(updateStatusOrderUseCase).execute(order.getId(), StatusEnum.CLOSED_NO_CREDIT);
        verify(resetStockUseCase).execute(order);
        verifyNoInteractions(updatePaymentOrderUseCase); // Ensure updatePaymentOrderUseCase is not called
    }
}