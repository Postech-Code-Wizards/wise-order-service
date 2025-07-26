package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.LowStockGateway;
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
class LowStockUseCaseUnitTest {

    @Mock
    private OrderToStockDTO orderToStockDTO;

    @Mock
    private LowStockGateway lowStockGateway;

    @InjectMocks
    private LowStockUseCase lowStockUseCase;

    @Test
    @DisplayName("Should convert order to stock DTOs and send them to low stock gateway")
    void execute_ShouldConvertOrderAndSendToGateway() {
        // Given an order and a list of expected StockDTOs
        Order order = Instancio.create(Order.class);
        List<StockDTO> expectedStockDTOs = Instancio.ofList(StockDTO.class).size(2).create(); // Example size

        // Configure mocks: when orderToStockDTO.execute is called with the order, return the expected StockDTOs
        when(orderToStockDTO.execute(order)).thenReturn(expectedStockDTOs);

        // When the execute method of LowStockUseCase is called
        lowStockUseCase.execute(order);

        // Then verify that orderToStockDTO.execute was called with the correct order
        verify(orderToStockDTO).execute(order);
        // And verify that lowStockGateway.send was called with the generated stock DTOs
        verify(lowStockGateway).send(expectedStockDTOs);
    }

}