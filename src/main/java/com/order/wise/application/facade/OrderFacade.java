package com.order.wise.application.facade;

import com.order.wise.application.usecase.ProcessOrderUseCase;
import com.order.wise.domain.Pedido;
import com.order.wise.gateway.database.converter.PedidoConverter;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final ProcessOrderUseCase processOrderUseCase;
    private final PedidoConverter pedidoConverter;

    public void receiverOrder(OrderDTO orderDTO) {

        Pedido pedido = pedidoConverter.toPedido(orderDTO);
        processOrderUseCase.execute(pedido);
    }
}
