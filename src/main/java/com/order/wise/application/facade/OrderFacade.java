package com.order.wise.application.facade;

import com.order.wise.application.facade.converter.PedidoDTOToDomain;
import com.order.wise.application.usecase.ProcessOrderUseCase;
import com.order.wise.domain.Pedido;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final ProcessOrderUseCase processOrderUseCase;
    private final PedidoDTOToDomain pedidoDTOToDomain;

    public void receiverOrder(OrderDTO orderDTO) {

        Pedido pedido = pedidoDTOToDomain.execute(orderDTO);
        processOrderUseCase.execute(pedido);
    }
}