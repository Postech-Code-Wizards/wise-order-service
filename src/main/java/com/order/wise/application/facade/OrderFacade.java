package com.order.wise.application.facade;

import com.order.wise.application.facade.converter.OrderDTOToDomain;
import com.order.wise.application.usecase.ProcessOrderUseCase;
import com.order.wise.domain.Order;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final ProcessOrderUseCase processOrderUseCase;
    private final OrderDTOToDomain orderDTOToDomain;

    public void receiverOrder(OrderDTO orderDTO) {
        Order order = orderDTOToDomain.execute(orderDTO);
        processOrderUseCase.execute(order);
    }

}