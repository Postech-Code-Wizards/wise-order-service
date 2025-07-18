package com.order.wise.application.usecase;

import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateStatusOrderUseCase {

    private final OrderGateway orderGateway;

    public void execute(Long orderId, StatusEnum status) {
        orderGateway.updateStatus(orderId, status);
    }

}
