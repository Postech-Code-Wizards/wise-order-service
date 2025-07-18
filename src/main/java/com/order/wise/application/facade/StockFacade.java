package com.order.wise.application.facade;

import com.order.wise.application.usecase.UpdateStatusOrderUseCase;
import com.order.wise.domain.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockFacade {

    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;

    public void stockResponse(Long orderId, boolean status) {
        if(!status) {
            updateStatusOrderUseCase.execute(orderId, StatusEnum.CLOSED_OUT_OF_STOCK);
        }
    }
}
