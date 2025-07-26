package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
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
class ProcessOrderUseCaseUnitTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private LowStockUseCase lowStockUseCase;

    @InjectMocks
    private ProcessOrderUseCase processOrderUseCase;

    @Test
    @DisplayName("Should create order and then process low stock for the saved order")
    void execute_ShouldCreateOrderAndProcessLowStock() {
        Order incomingOrder = Instancio.create(Order.class);
        Order savedOrder = Instancio.create(Order.class);

        when(createOrderUseCase.createOrder(incomingOrder)).thenReturn(savedOrder);

        processOrderUseCase.execute(incomingOrder);

        verify(createOrderUseCase).createOrder(incomingOrder);
        verify(lowStockUseCase).execute(savedOrder);
    }
}