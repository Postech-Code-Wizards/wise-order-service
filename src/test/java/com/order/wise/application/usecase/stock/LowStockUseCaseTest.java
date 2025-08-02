package com.order.wise.application.usecase.stock;

import com.order.wise.application.facade.converter.OrderToStockDTO;
import com.order.wise.domain.Order;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.StockGateway;
import com.order.wise.gateway.rabbitmq.dto.StockDTO;
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
    private StockGateway lowStockGateway;

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private LowStockUseCase lowStockUseCase;

    @Test
    @DisplayName("Should convert order to stock DTOs and send them to low stock gateway")
    void execute_ShouldConvertOrderAndSendToGateway() {

        Order order = Instancio.create(Order.class);
        List<StockDTO> expectedStockDTOs = Instancio.ofList(StockDTO.class).size(2).create();

        when(orderToStockDTO.execute(order)).thenReturn(expectedStockDTOs);
        when(orderGateway.findById(order.getId())).thenReturn(order);

        lowStockUseCase.execute(order.getId());

        verify(orderToStockDTO).execute(order);
        verify(lowStockGateway).low(expectedStockDTOs);
    }

}