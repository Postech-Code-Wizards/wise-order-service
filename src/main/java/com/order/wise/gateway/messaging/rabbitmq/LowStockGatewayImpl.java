package com.order.wise.gateway.messaging.rabbitmq;

import com.order.wise.gateway.LowStockGateway;
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
public class LowStockGatewayImpl implements LowStockGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(List<StockDTO> stockDTO) {

        log.info("[{}] Sending low stock notification to RabbitMQ.", this.getClass().getSimpleName());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTO);
    }

}
