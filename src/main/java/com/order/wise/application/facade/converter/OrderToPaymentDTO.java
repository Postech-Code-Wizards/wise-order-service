package com.order.wise.application.facade.converter;

import com.order.wise.domain.Order;
import com.order.wise.gateway.messaging.rabbitmq.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderToPaymentDTO {

    public PaymentDTO execute(Order pedido) {
        return PaymentDTO.builder()
                .totalValue(pedido.getTotalValue())
                .orderId(pedido.getId())
                .clientId(pedido.getClientId())
                .creditCardNumber(pedido.getCreditCardNumber())
                .build();

    }
}
