package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.ResetStockGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResetStockUseCaseUnitTest {

    @Mock
    private OrderToStockDTO orderToStockDTO;

    @Mock
    private ResetStockGateway resetStockGateway;

    @InjectMocks
    private ResetStockUseCase resetStockUseCase;

    @Test
    @DisplayName("Should convert order to stock DTOs and send them to reset stock gateway")
    void execute_ShouldConvertOrderAndSendToGateway() {
        Order order = Instancio.create(Order.class);
        List<StockDTO> expectedStockDTOs = Instancio.ofList(StockDTO.class).size(3).create();

        when(orderToStockDTO.execute(order)).thenReturn(expectedStockDTOs);

        resetStockUseCase.execute(order);

        verify(orderToStockDTO).execute(order);
        verify(resetStockGateway).send(expectedStockDTOs);
    }

}