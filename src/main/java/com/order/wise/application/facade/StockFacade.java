package com.order.wise.application.facade;

import com.order.wise.application.usecase.stock.ProcessCallbackStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class StockFacade {

    private final ProcessCallbackStockUseCase processCallbackStockUseCase;

    public void stockResponse(BigInteger orderId, boolean status) {
        processCallbackStockUseCase.execute(orderId, status);
    }
}
