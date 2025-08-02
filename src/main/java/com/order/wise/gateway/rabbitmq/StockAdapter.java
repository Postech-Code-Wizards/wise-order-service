package com.order.wise.gateway.rabbitmq;

import com.order.wise.gateway.StockGateway;
import com.order.wise.gateway.rabbitmq.dto.StockDTO;
import com.order.wise.infrastructure.rabbitmq.configuration.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class StockAdapter implements StockGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void low(List<StockDTO> stockDTO) {

        log.info("[{}] Sending low stock notification to RabbitMQ.", this.getClass().getSimpleName());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTO);
    }

    @Override
    public void reset(List<StockDTO> stockDTO) {

        log.info("[{}] Sending reset stock notification to RabbitMQ.", this.getClass().getSimpleName());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_RETURN_ROUTING_KEY,
                stockDTO);
    }

}
