package com.order.wise.application.usecase.stock;

import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessCallbackStockUseCase {

    private final OrderGateway orderGateway;

    public void execute(BigInteger orderId, boolean status) {
        if(!status) {
            orderGateway.updateStatus(orderId, StatusEnum.CLOSED_OUT_OF_STOCK);
        }
    }
}
