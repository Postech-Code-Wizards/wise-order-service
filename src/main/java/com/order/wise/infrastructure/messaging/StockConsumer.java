package com.order.wise.infrastructure.messaging;

import com.order.wise.application.facade.StockFacade;
import com.order.wise.infrastructure.messaging.dto.StockResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockConsumer {

    private final StockFacade stockFacade;

    @RabbitListener(queues = RabbitMQConfig.STOCK_RESPONSE_QUEUE_NAME)
    public void execute(StockResponseDTO stockResponseDTO) {
        log.info("Received stock response message: {}", stockResponseDTO.toString());
        stockFacade.stockResponse(stockResponseDTO.getPedidoId(), stockResponseDTO.getStatus());
    }

}
