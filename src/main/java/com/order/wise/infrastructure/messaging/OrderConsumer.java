package com.order.wise.infrastructure.messaging;

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

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE_NAME)
    public void receiveOrderMessage(String message) {

        log.info("Received order message: {}", message);
        // todo - Converter a message para o OrderDTO
        // todo - Aqui você pode usar um conversor ou mapper para transformar a mensagem em um objeto OrderDTO
        // todo - Por exemplo, se a mensagem for um JSON, você pode usar uma biblioteca como Jackson para fazer a conversão
        OrderDTO orderDTO = new OrderDTO();
        orderFacade.receiverOrder(orderDTO);

    }
}
