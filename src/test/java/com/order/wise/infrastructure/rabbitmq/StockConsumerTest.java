package com.order.wise.infrastructure.rabbitmq;

import com.order.wise.application.facade.StockFacade;
import com.order.wise.infrastructure.rabbitmq.dto.StockResponseDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StockConsumerUnitTest {

    @Mock
    private StockFacade stockFacade;

    @InjectMocks
    private StockConsumer stockConsumer;

    @Test
    @DisplayName("Should process stock response and call stock facade correctly")
    void execute_ShouldProcessStockResponse() {

        StockResponseDTO stockResponseDTO = Instancio.create(StockResponseDTO.class);

        stockConsumer.execute(stockResponseDTO);

        verify(stockFacade).stockResponse(stockResponseDTO.getPedidoId(), stockResponseDTO.getStatus());
    }
}