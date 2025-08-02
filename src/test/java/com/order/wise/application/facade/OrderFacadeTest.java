package com.order.wise.application.facade;

import com.order.wise.application.facade.converter.OrderDTOToDomain;
import com.order.wise.application.usecase.order.ProcessOrderUseCase;
import com.order.wise.domain.Order;
import com.order.wise.infrastructure.rabbitmq.dto.OrderDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderFacadeUnitTest {

    @Mock
    private ProcessOrderUseCase processOrderUseCase;

    @Mock
    private OrderDTOToDomain orderDTOToDomain;

    @InjectMocks
    private OrderFacade orderFacade;

    @Test
    @DisplayName("Should receive order and process it correctly")
    void receiverOrder_ShouldReceiveAndProcessOrder() {

        OrderDTO orderDTO = Instancio.create(OrderDTO.class);
        Order order = Instancio.create(Order.class);

        when(orderDTOToDomain.execute(orderDTO)).thenReturn(order);

        orderFacade.receiverOrder(orderDTO);

        verify(orderDTOToDomain).execute(orderDTO);
        verify(processOrderUseCase).execute(order);
    }
}