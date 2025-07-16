package com.order.wise.gateway;

import com.order.wise.gateway.messaging.rabbitmq.dto.PaymentDTO;

public interface PaymentGateway {

    void send(PaymentDTO paymentDTO);

}
