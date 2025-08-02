package com.order.wise.infrastructure.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.wise.application.facade.OrderFacade;
import com.order.wise.infrastructure.rabbitmq.dto.OrderDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderConsumerUnitTest {

    @Mock
    private OrderFacade orderFacade;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private OrderConsumer orderConsumer;

    @Test
    @DisplayName("Should successfully process a valid order message")
    void receiveOrderMessage_ShouldProcessValidMessage() throws Exception {

        String message = Instancio.create(String.class);
        OrderDTO orderDTO = Instancio.create(OrderDTO.class);

        when(objectMapper.readValue(message, OrderDTO.class)).thenReturn(orderDTO);

        orderConsumer.receiveOrderMessage(message);

        verify(objectMapper).readValue(message, OrderDTO.class);
        verify(orderFacade).receiverOrder(orderDTO);
    }

    @Test
    @DisplayName("Should handle exception when message parsing fails")
    void receiveOrderMessage_ShouldHandleParsingException() throws Exception {

        String invalidMessage = "invalid json string";

        doThrow(new RuntimeException("Parsing error")).when(objectMapper).readValue(invalidMessage, OrderDTO.class);

        orderConsumer.receiveOrderMessage(invalidMessage);

        verify(objectMapper).readValue(invalidMessage, OrderDTO.class);
        verifyNoInteractions(orderFacade);
    }

    @Test
    @DisplayName("Should handle exception when order facade processing fails")
    void receiveOrderMessage_ShouldHandleFacadeProcessingException() throws Exception {

        String message = Instancio.create(String.class);
        OrderDTO orderDTO = Instancio.create(OrderDTO.class);

        when(objectMapper.readValue(message, OrderDTO.class)).thenReturn(orderDTO);

        doThrow(new RuntimeException("Facade processing error")).when(orderFacade).receiverOrder(any(OrderDTO.class));

        orderConsumer.receiveOrderMessage(message);

        verify(objectMapper).readValue(message, OrderDTO.class);
        verify(orderFacade).receiverOrder(orderDTO);
    }
}