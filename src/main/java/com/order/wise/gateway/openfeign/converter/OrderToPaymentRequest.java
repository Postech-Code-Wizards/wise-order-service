package com.order.wise.gateway.openfeign.converter;

import com.order.wise.domain.Order;
import com.order.wise.gateway.openfeign.request.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderToPaymentRequest {

    public PaymentRequest execute(Order order) {
        return PaymentRequest.builder()
                .totalValue(order.getTotalValue().toString())
                .creditCard(order.getCreditCardNumber())
                .build();

    }
}
