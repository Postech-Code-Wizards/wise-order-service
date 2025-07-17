package com.order.wise.application.facade;

import com.order.wise.application.usecase.UpdateStatusOrderUseCase;
import com.order.wise.domain.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockFacade {

    private final UpdateStatusOrderUseCase updateStatusPedidoUseCase;

    public void stockResponse(Long orderId, boolean status) {
        StatusEnum statusUpdate = status ? StatusEnum.OPEN : StatusEnum.CLOSED_OUT_OF_STOCK;
        updateStatusPedidoUseCase.execute(orderId, statusUpdate);
    }
}
