package com.order.wise.gateway.messaging.rabbitmq;

import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import com.order.wise.infrastructure.messaging.RabbitMQConfig;
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
class LowStockGatewayImplUnitTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private LowStockGatewayImpl lowStockGateway;

    @Test
    @DisplayName("Should send low stock notification to RabbitMQ with correct exchange, routing key, and payload")
    void send_ShouldSendToRabbitMQ() {

        List<StockDTO> stockDTOs = Instancio.ofList(StockDTO.class).size(2).create();

        lowStockGateway.send(stockDTOs);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTOs
        );
    }

    @Test
    @DisplayName("Should send empty list of StockDTOs to RabbitMQ")
    void send_ShouldSendEmptyListToRabbitMQ() {

        List<StockDTO> stockDTOs = List.of();

        lowStockGateway.send(stockDTOs);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.STOCK_ROUTING_KEY,
                stockDTOs
        );
    }
}