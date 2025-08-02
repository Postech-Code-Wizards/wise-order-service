package com.order.wise.gateway.rabbitmq;

import com.order.wise.gateway.rabbitmq.dto.StockDTO;
import com.order.wise.infrastructure.rabbitmq.configuration.RabbitMQConfig;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StockAdapterTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private StockAdapter stockAdapter;

    @Test
    @DisplayName("Should send reset stock notification to RabbitMQ with correct exchange, routing key, and payload")
    void send_ShouldSendResetToRabbitMQ() {

        List<StockDTO> stockDTOs = Instancio.ofList(StockDTO.class).size(2).create();

        stockAdapter.reset(stockDTOs);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_RETURN_ROUTING_KEY,
                stockDTOs
        );
    }

    @Test
    @DisplayName("Should send empty list of StockDTOs to RabbitMQ")
    void send_ShouldSendResetEmptyListToRabbitMQ() {

        List<StockDTO> stockDTOs = List.of();

        stockAdapter.reset(stockDTOs);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_RETURN_ROUTING_KEY,
                stockDTOs
        );
    }

    @Test
    @DisplayName("Should send low stock notification to RabbitMQ with correct exchange, routing key, and payload")
    void send_ShouldSendLowToRabbitMQ() {

        List<StockDTO> stockDTOs = Instancio.ofList(StockDTO.class).size(2).create();

        stockAdapter.low(stockDTOs);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTOs
        );
    }

    @Test
    @DisplayName("Should send empty list of StockDTOs to RabbitMQ")
    void send_ShouldSendLowEmptyListToRabbitMQ() {

        List<StockDTO> stockDTOs = List.of();

        stockAdapter.low(stockDTOs);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTOs
        );
    }

}