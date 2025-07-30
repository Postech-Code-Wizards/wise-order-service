package com.order.wise.application.usecase.stock;

import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class ProcessCallbackStockUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private ProcessCallbackStockUseCase processCallbackStockUseCase;

    @Test
    @DisplayName("Should update order status to CLOSED_OUT_OF_STOCK when status is false")
    void execute_statusFalse_shouldUpdateOrderStatus() {

        BigInteger orderId = Instancio.create(BigInteger.class);
        boolean status = false;

        processCallbackStockUseCase.execute(orderId, status);

        verify(orderGateway).updateStatus(orderId, StatusEnum.CLOSED_OUT_OF_STOCK);
    }

    @Test
    @DisplayName("Should not update order status when status is true")
    void execute_statusTrue_shouldNotUpdateOrderStatus() {

        BigInteger orderId = Instancio.create(BigInteger.class);
        boolean status = true;

        processCallbackStockUseCase.execute(orderId, status);

        verify(orderGateway, never()).updateStatus(orderId, StatusEnum.CLOSED_OUT_OF_STOCK);
    }
}
