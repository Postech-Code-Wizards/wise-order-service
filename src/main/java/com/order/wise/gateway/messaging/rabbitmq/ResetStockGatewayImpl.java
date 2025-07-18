package com.order.wise.gateway.messaging.rabbitmq;

import com.order.wise.gateway.ResetStockGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import com.order.wise.infrastructure.messaging.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ResetStockGatewayImpl implements ResetStockGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(List<StockDTO> stockDTO) {

        log.info("[{}] Sending reset stock notification to RabbitMQ.", this.getClass().getSimpleName());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.STOCK_REPOR_QUEUE,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTO);
    }
}
