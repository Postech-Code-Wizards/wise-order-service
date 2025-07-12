package com.order.wise.gateway;

import com.order.wise.gateway.messaging.rabbitMQ.dto.PaymentDTO;
import com.order.wise.gateway.messaging.rabbitMQ.dto.StockDTO;

public interface PaymentGateway {

    void send(PaymentDTO paymentDTO);

}
