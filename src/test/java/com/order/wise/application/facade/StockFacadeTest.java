package com.order.wise.application.facade;

import com.order.wise.application.usecase.stock.ProcessCallbackStockUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.instancio.Instancio;

import java.math.BigInteger;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StockFacadeTest {

    @Mock
    private ProcessCallbackStockUseCase processCallbackStockUseCase;

    @InjectMocks
    private StockFacade stockFacade;

    @Test
    @DisplayName("Should call processCallbackStockUseCase.execute with the correct arguments")
    void stockResponseTest() {
        BigInteger orderId = Instancio.create(BigInteger.class);
        boolean status = Instancio.create(Boolean.class);

        stockFacade.stockResponse(orderId, status);

        verify(processCallbackStockUseCase).execute(orderId, status);
    }
}