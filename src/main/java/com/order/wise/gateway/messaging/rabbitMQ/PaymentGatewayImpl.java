package com.order.wise.gateway.messaging.rabbitMQ;

import com.order.wise.gateway.PaymentGateway;
import com.order.wise.gateway.messaging.rabbitMQ.dto.PaymentDTO;
import com.order.wise.infrastructure.messaging.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(PaymentDTO paymentDTO) {
        log.info("[{}] Sending payment notification to RabbitMQ.", this.getClass().getSimpleName());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.PAYMENT_ROUTING_KEY,
                paymentDTO);

    }

}
