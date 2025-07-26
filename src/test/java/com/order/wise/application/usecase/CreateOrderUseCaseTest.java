package com.order.wise.application.usecase;

import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseUnitTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @Test
    @DisplayName("Should create and save a new order successfully")
    void createOrder_ShouldCreateAndSaveOrder() {

        Order orderToSave = Instancio.create(Order.class);
        Order savedOrder = Instancio.create(Order.class);

        when(orderGateway.save(orderToSave)).thenReturn(savedOrder);

        Order resultOrder = createOrderUseCase.createOrder(orderToSave);

        assertThat(resultOrder).isEqualTo(savedOrder);
    }
}