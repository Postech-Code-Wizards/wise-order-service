package com.order.wise.application.usecase;

import com.order.wise.gateway.OrderGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdatePaymentOrderUseCaseUnitTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private UpdatePaymentOrderUseCase updatePaymentOrderUseCase;

    @Test
    @DisplayName("Should update payment information for a given order ID and payment ID")
    void execute_ShouldUpdatePaymentForOrder() {
        Long orderId = Instancio.create(Long.class);
        String paymentId = Instancio.create(String.class);

        updatePaymentOrderUseCase.execute(orderId, paymentId);

        verify(orderGateway).updatePayment(orderId, paymentId);
    }
}