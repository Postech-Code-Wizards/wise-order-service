package com.order.wise.application.facade;

import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    public void receiverOrder(OrderDTO orderDTO) {

        // todo - Aqui você deve chamar o use case que irá processar o pedido recebido
        // ProcessOrderUseCase.excute
    }
}
