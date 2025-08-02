package com.order.wise.infrastructure.rabbitmq;

import com.order.wise.infrastructure.rabbitmq.configuration.RabbitMQConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RabbitMQConfigUnitTest {

    private final RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

    @Test
    @DisplayName("Should create a DirectExchange with the correct name")
    void directExchange_ShouldCreateCorrectExchange() {
        DirectExchange exchange = rabbitMQConfig.directExchange();
        assertThat(exchange).isNotNull();
        assertThat(exchange.getName()).isEqualTo(RabbitMQConfig.EXCHANGE_NAME);
        assertThat(exchange.isDurable()).isTrue();
        assertThat(exchange.isAutoDelete()).isFalse();
    }

    @Test
    @DisplayName("Should create a durable Stock Queue with the correct name")
    void stockQueue_ShouldCreateCorrectQueue() {
        Queue queue = rabbitMQConfig.stockQueue();
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo(RabbitMQConfig.STOCK_QUEUE_NAME);
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
    }

    @Test
    @DisplayName("Should create a durable Stock Response Queue with the correct name")
    void stockRespostaQueue_ShouldCreateCorrectQueue() {
        Queue queue = rabbitMQConfig.stockRespostaQueue();
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo(RabbitMQConfig.STOCK_RESPONSE_QUEUE_NAME);
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
    }

    @Test
    @DisplayName("Should create a durable Payment Queue with the correct name")
    void paymentQueue_ShouldCreateCorrectQueue() {
        Queue queue = rabbitMQConfig.paymentQueue();
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo(RabbitMQConfig.PAYMENT_QUEUE_NAME);
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
    }

    @Test
    @DisplayName("Should create a durable Stock Return Queue with the correct name")
    void stockReturnQueue_ShouldCreateCorrectQueue() {
        Queue queue = rabbitMQConfig.stockReturnQueue();
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo(RabbitMQConfig.STOCK_RETURN_QUEUE_NAME);
        assertThat(queue.isDurable()).isTrue();
        assertThat(queue.isAutoDelete()).isFalse();
    }

    @Test
    @DisplayName("Should create a correct Stock Binding to DirectExchange")
    void stockBinding_ShouldCreateCorrectBinding() {

        Queue stockQueue = rabbitMQConfig.stockQueue();
        DirectExchange directExchange = rabbitMQConfig.directExchange();

        Binding binding = rabbitMQConfig.stockBinding(stockQueue, directExchange);
        assertThat(binding).isNotNull();
        assertThat(binding.getExchange()).isEqualTo(RabbitMQConfig.EXCHANGE_NAME);
        assertThat(binding.getRoutingKey()).isEqualTo(RabbitMQConfig.STOCK_ROUTING_KEY);
        assertThat(binding.getDestination()).isEqualTo(RabbitMQConfig.STOCK_QUEUE_NAME);
        assertThat(binding.getDestinationType()).isEqualTo(Binding.DestinationType.QUEUE);
    }

    @Test
    @DisplayName("Should create a correct Stock Return Binding to DirectExchange")
    void stockReturnBinding_ShouldCreateCorrectBinding() {

        Queue stockReturnQueue = rabbitMQConfig.stockReturnQueue();
        DirectExchange directExchange = rabbitMQConfig.directExchange();

        Binding binding = rabbitMQConfig.stockReturnBinding(stockReturnQueue, directExchange);
        assertThat(binding).isNotNull();
        assertThat(binding.getExchange()).isEqualTo(RabbitMQConfig.EXCHANGE_NAME);
        assertThat(binding.getRoutingKey()).isEqualTo(RabbitMQConfig.STOCK_RETURN_ROUTING_KEY);
        assertThat(binding.getDestination()).isEqualTo(RabbitMQConfig.STOCK_RETURN_QUEUE_NAME);
        assertThat(binding.getDestinationType()).isEqualTo(Binding.DestinationType.QUEUE);
    }

    @Test
    @DisplayName("Should create a Jackson2JsonMessageConverter")
    void jsonMessageConverter_ShouldCreateConverter() {
        Jackson2JsonMessageConverter converter = rabbitMQConfig.jsonMessageConverter();
        assertThat(converter).isNotNull();
    }

    @Test
    @DisplayName("Should create a RabbitTemplate with the correct ConnectionFactory and MessageConverter")
    void rabbitTemplate_ShouldCreateTemplate() {

        ConnectionFactory mockConnectionFactory = mock(ConnectionFactory.class);

        RabbitTemplate template = rabbitMQConfig.rabbitTemplate(mockConnectionFactory);
        assertThat(template).isNotNull();
        assertThat(template.getConnectionFactory()).isEqualTo(mockConnectionFactory);
        assertThat(template.getMessageConverter()).isInstanceOf(Jackson2JsonMessageConverter.class);
    }
}