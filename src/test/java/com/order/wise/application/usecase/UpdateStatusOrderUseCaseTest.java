package com.order.wise.application.usecase;

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

@ExtendWith(MockitoExtension.class)
class UpdateStatusOrderUseCaseUnitTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private UpdateStatusOrderUseCase updateStatusOrderUseCase;

    @Test
    @DisplayName("Should update the status of a given order ID")
    void execute_ShouldUpdateOrderStatus() {

        Long orderId = Instancio.create(Long.class);
        StatusEnum status = Instancio.create(StatusEnum.class); // Instancio will pick a random enum value

        updateStatusOrderUseCase.execute(orderId, status);

        verify(orderGateway).updateStatus(orderId, status);
    }

    @Test
    @DisplayName("Should update status to OPEN for a specific order ID")
    void execute_ShouldUpdateStatusToOpen() {

        Long orderId = Instancio.create(Long.class);
        StatusEnum status = StatusEnum.OPEN;

        updateStatusOrderUseCase.execute(orderId, status);

        verify(orderGateway).updateStatus(orderId, StatusEnum.OPEN);
    }

    @Test
    @DisplayName("Should update status to CLOSED_SUCCESSFULLY for a specific order ID")
    void execute_ShouldUpdateStatusToClosedSuccessfully() {

        Long orderId = Instancio.create(Long.class);
        StatusEnum status = StatusEnum.CLOSED_SUCCESSFULLY;

        updateStatusOrderUseCase.execute(orderId, status);

        verify(orderGateway).updateStatus(orderId, StatusEnum.CLOSED_SUCCESSFULLY);
    }
}