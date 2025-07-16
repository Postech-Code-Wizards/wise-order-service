package com.order.wise.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.wise.application.facade.OrderFacade;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderFacade orderFacade;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE_NAME)
    public void receiveOrderMessage(String message) {

        log.info("Received order message: {}", message);
        try {
            OrderDTO orderDTO = objectMapper.readValue(message, OrderDTO.class);
            orderFacade.receiverOrder(orderDTO);
        } catch (Exception e) {
            log.error("Failed to process order message: {}", message, e);
        }
    }
}
