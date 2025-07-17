package com.order.wise.infrastructure.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "order_exchange";
    public static final String ORDER_QUEUE_NAME = "order_queue";
    public static final String STOCK_QUEUE_NAME = "stock-baixa-queue";
    public static final String STOCK_RESPONSE_QUEUE_NAME ="stock-resposta-queue";
    public static final String PAYMENT_QUEUE_NAME = "payment_queue";
    public static final String STOCK_ROUTING_KEY = "stock-baixa-routing-key";
    public static final String PAYMENT_ROUTING_KEY = "payment";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue stockQueue() {
        return new Queue(STOCK_QUEUE_NAME, true);
    }

    @Bean
    public Queue stockRespostaQueue() {
        return new Queue(STOCK_RESPONSE_QUEUE_NAME, true);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE_NAME, true);
    }

    @Bean
    public Binding stockBinding(Queue stockQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(stockQueue).to(directExchange).with(STOCK_ROUTING_KEY);
    }

    @Bean
    public Binding paymentBinding(Queue paymentQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(paymentQueue).to(directExchange).with(PAYMENT_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
